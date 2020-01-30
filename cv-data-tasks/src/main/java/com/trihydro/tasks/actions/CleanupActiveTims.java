package com.trihydro.tasks.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.trihydro.library.helpers.Utility;
import com.trihydro.library.model.ActiveTim;
import com.trihydro.library.service.ActiveTimService;
import com.trihydro.library.service.RestTemplateProvider;
import com.trihydro.tasks.config.DataTasksConfiguration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public class CleanupActiveTims implements Runnable {
    private DataTasksConfiguration configuration;

    public CleanupActiveTims(DataTasksConfiguration configuration) {
        this.configuration = configuration;
    }

    public void run() {
        Utility.logWithDate("CleanupActiveTims - Running...");

        try {
            List<ActiveTim> activeTims = new ArrayList<ActiveTim>();
            List<ActiveTim> tmp = null;

            // select active tims missing ITIS codes
            tmp = ActiveTimService.getActiveTimsMissingItisCodes();
            if (tmp.size() > 0) {
                Utility.logWithDate("CleanupActiveTims - Found " + tmp.size() + " Active TIMs missing ITIS Codes");
                activeTims.addAll(tmp);
            }

            // add active tims that weren't sent to the SDX or any RSUs
            tmp = ActiveTimService.getActiveTimsNotSent();
            if (tmp.size() > 0) {
                Utility.logWithDate(
                        "CleanupActiveTims - Found " + tmp.size() + " Active TIMs that weren't distributed");
                activeTims.addAll(tmp);
            }

            if (activeTims.size() == 0) {
                Utility.logWithDate("CleanupActiveTims - Found 0 Active TIMs");
            }

            // delete from rsus and the SDX
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = null;
            String activeTimJson;
            Gson gson = new Gson();

            // send to tim type endpoint to delete from RSUs and SDWs
            for (ActiveTim activeTim : activeTims) {

                activeTimJson = gson.toJson(activeTim);
                entity = new HttpEntity<String>(activeTimJson, headers);

                Utility.logWithDate(
                        "CleanupActiveTims - Deleting ActiveTim: { activeTimId: " + activeTim.getActiveTimId() + " }");
                RestTemplateProvider.GetRestTemplate().exchange(configuration.getWrapperUrl() + "/delete-tim/",
                        HttpMethod.DELETE, entity, String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // don't rethrow error, or the task won't be reran until the service is
            // restarted.
        }
    }
}