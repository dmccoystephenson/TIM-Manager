package com.trihydro.library.helpers;

// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.trihydro.library.model.Milepost;

import org.junit.Test;

public class MilepostReductionTest {

    private MilepostReduction uut = new MilepostReduction();

    @Test
    public void applyMilepostReductionAlorithm_SUCCESS() {
        // Arrange
        List<Milepost> mps = getMileposts();

        // Act
        List<Milepost> reduced = uut.applyMilepostReductionAlorithm(mps, Double.valueOf(327 / 2));

        // Assert
        assertTrue(mps.size() > reduced.size());
    }

    @Test
    public void applyMilepostReductionAlorithm_SUCCESS_Vsl() {
        // Arrange
        List<Milepost> mps = getMpVsl();

        // Act
        List<Milepost> reduced = uut.applyMilepostReductionAlorithm(mps, Double.valueOf(327 / 2));

        // Assert
        assertTrue(mps.size() > reduced.size());
    }

    @Test
    public void applyMilepostReductionAlorithm_NULL() {
        // Arrange

        // Act
        List<Milepost> reduced = uut.applyMilepostReductionAlorithm(null, Double.valueOf(327 / 2));

        // Assert
        assertNull(reduced);
    }

    @Test
    public void applyMilepostReductionAlorithm_empty() {
        // Arrange
        List<Milepost> mps = new ArrayList<>();

        // Act
        List<Milepost> reduced = uut.applyMilepostReductionAlorithm(mps, Double.valueOf(327 / 2));

        // Assert
        assertEquals(0, reduced.size());
    }

    private List<Milepost> getMileposts() {
        List<Milepost> mps = new ArrayList<>();
        Milepost mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35108594);
        mp.setLongitude(-105.71982297);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35080826);
        mp.setLongitude(-105.71791726);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3505391);
        mp.setLongitude(-105.71600946);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35026563);
        mp.setLongitude(-105.71410271);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34998857);
        mp.setLongitude(-105.71219687);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34971931);
        mp.setLongitude(-105.71028907);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34944341);
        mp.setLongitude(-105.70838296);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34917608);
        mp.setLongitude(-105.70647477);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34900264);
        mp.setLongitude(-105.70454866);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34909178);
        mp.setLongitude(-105.70261381);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34942922);
        mp.setLongitude(-105.70074013);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3498824);
        mp.setLongitude(-105.69890686);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35028683);
        mp.setLongitude(-105.69705483);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35045968);
        mp.setLongitude(-105.69514162);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35046923);
        mp.setLongitude(-105.69321245);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35047214);
        mp.setLongitude(-105.69128312);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35047525);
        mp.setLongitude(-105.6893538);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35048283);
        mp.setLongitude(-105.6874245);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35048667);
        mp.setLongitude(-105.68549518);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3504909);
        mp.setLongitude(-105.68356586);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35049338);
        mp.setLongitude(-105.68162168);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35050122);
        mp.setLongitude(-105.67967751);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35050295);
        mp.setLongitude(-105.67773333);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35050541);
        mp.setLongitude(-105.67578914);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35050841);
        mp.setLongitude(-105.67384496);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35051591);
        mp.setLongitude(-105.6719008);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35051979);
        mp.setLongitude(-105.66995663);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35052528);
        mp.setLongitude(-105.66801246);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35052552);
        mp.setLongitude(-105.66606832);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35053116);
        mp.setLongitude(-105.66412415);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35053552);
        mp.setLongitude(-105.66221394);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35053841);
        mp.setLongitude(-105.66030372);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35053857);
        mp.setLongitude(-105.65839351);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35054359);
        mp.setLongitude(-105.65648329);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35055043);
        mp.setLongitude(-105.65457309);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35055435);
        mp.setLongitude(-105.65266287);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35055749);
        mp.setLongitude(-105.65075265);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35056059);
        mp.setLongitude(-105.64884242);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35056299);
        mp.setLongitude(-105.6469322);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35055867);
        mp.setLongitude(-105.64502208);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35043367);
        mp.setLongitude(-105.64311038);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.35010638);
        mp.setLongitude(-105.64124167);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34959611);
        mp.setLongitude(-105.63944697);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34890277);
        mp.setLongitude(-105.63776277);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34815556);
        mp.setLongitude(-105.6361185);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34740819);
        mp.setLongitude(-105.63447439);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34666284);
        mp.setLongitude(-105.63282867);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34591768);
        mp.setLongitude(-105.63118282);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34517272);
        mp.setLongitude(-105.62953684);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34442506);
        mp.setLongitude(-105.62789302);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34367193);
        mp.setLongitude(-105.62622947);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34291748);
        mp.setLongitude(-105.62456699);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34215755);
        mp.setLongitude(-105.62290894);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.34140558);
        mp.setLongitude(-105.62124455);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3405704);
        mp.setLongitude(-105.61965357);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33950715);
        mp.setLongitude(-105.61832822);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33823816);
        mp.setLongitude(-105.61737323);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33683509);
        mp.setLongitude(-105.61683909);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33537947);
        mp.setLongitude(-105.61665241);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3339197);
        mp.setLongitude(-105.6165228);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33253561);
        mp.setLongitude(-105.61640304);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.33115154);
        mp.setLongitude(-105.61628292);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32976762);
        mp.setLongitude(-105.61615966);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32838367);
        mp.setLongitude(-105.61603674);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32699958);
        mp.setLongitude(-105.61591691);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3256154);
        mp.setLongitude(-105.61579875);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32423148);
        mp.setLongitude(-105.61567537);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32284734);
        mp.setLongitude(-105.61555622);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32146327);
        mp.setLongitude(-105.61543583);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.32007908);
        mp.setLongitude(-105.61531771);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31861461);
        mp.setLongitude(-105.6151909);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31715018);
        mp.setLongitude(-105.61506327);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31568573);
        mp.setLongitude(-105.6149361);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31422132);
        mp.setLongitude(-105.61480798);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31275679);
        mp.setLongitude(-105.61468218);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.31129252);
        mp.setLongitude(-105.61455132);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30982806);
        mp.setLongitude(-105.61442426);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30836348);
        mp.setLongitude(-105.61429962);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30689907);
        mp.setLongitude(-105.61417133);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30544029);
        mp.setLongitude(-105.61396925);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30413484);
        mp.setLongitude(-105.61339994);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3029801);
        mp.setLongitude(-105.61241066);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30205978);
        mp.setLongitude(-105.61105758);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.3014131);
        mp.setLongitude(-105.60944887);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30094584);
        mp.setLongitude(-105.60773014);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30048258);
        mp.setLongitude(-105.60600929);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.30001744);
        mp.setLongitude(-105.60428936);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.2995553);
        mp.setLongitude(-105.60256801);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29909199);
        mp.setLongitude(-105.60084723);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29862816);
        mp.setLongitude(-105.59912671);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29812221);
        mp.setLongitude(-105.59724882);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.2976113);
        mp.setLongitude(-105.59535359);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29710355);
        mp.setLongitude(-105.5934569);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29659485);
        mp.setLongitude(-105.59156067);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29608238);
        mp.setLongitude(-105.58966622);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29556906);
        mp.setLongitude(-105.5877722);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29506105);
        mp.setLongitude(-105.58587568);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29455088);
        mp.setLongitude(-105.58398019);
        mps.add(mp);

        mp = new Milepost();
        mp.setCommonName("I 80");
        mp.setLatitude(41.29404404);
        mp.setLongitude(-105.58208315);
        mps.add(mp);

        return mps;
    }

    private List<Milepost> getMpVsl() {
        List<Milepost> mps = new ArrayList<>();

        Milepost mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(353.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10961403);
        mp.setLongitude(-104.97337039);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(353.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10938468);
        mp.setLongitude(-104.97526035);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10915577);
        mp.setLongitude(-104.97715041);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10892524);
        mp.setLongitude(-104.9790401);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10869609);
        mp.setLongitude(-104.98093008);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.6);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10846731);
        mp.setLongitude(-104.98282012);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.5);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10823754);
        mp.setLongitude(-104.98470996);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.4);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10800988);
        mp.setLongitude(-104.98660025);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.3);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10777955);
        mp.setLongitude(-104.98848995);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.2);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10753733);
        mp.setLongitude(-104.990377);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10720193);
        mp.setLongitude(-104.99223849);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(352.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10673543);
        mp.setLongitude(-104.99404997);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.1062341);
        mp.setLongitude(-104.99584573);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10572808);
        mp.setLongitude(-104.99763919);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10522379);
        mp.setLongitude(-104.99943348);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.6);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10471756);
        mp.setLongitude(-105.00122681);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.5);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10421259);
        mp.setLongitude(-105.00302074);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.4);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10370475);
        mp.setLongitude(-105.00481325);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.3);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10320274);
        mp.setLongitude(-105.0066086);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.2);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10269448);
        mp.setLongitude(-105.00840086);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10219597);
        mp.setLongitude(-105.01019786);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(351.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10178354);
        mp.setLongitude(-105.01203167);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10150753);
        mp.setLongitude(-105.01390976);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10136087);
        mp.setLongitude(-105.01581304);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10135169);
        mp.setLongitude(-105.01772623);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.6);
        mp.setCommonName("I 80");
        mp.setLatitude(41.1014764);
        mp.setLongitude(-105.01963244);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.5);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10173509);
        mp.setLongitude(-105.02151484);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.4);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10204607);
        mp.setLongitude(-105.0233841);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.3);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10235774);
        mp.setLongitude(-105.02525317);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.2);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10266788);
        mp.setLongitude(-105.02712268);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10297865);
        mp.setLongitude(-105.02899203);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(350.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10328426);
        mp.setLongitude(-105.03086285);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10353963);
        mp.setLongitude(-105.0327461);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10365357);
        mp.setLongitude(-105.0346535);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10365505);
        mp.setLongitude(-105.03656687);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.6);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10361368);
        mp.setLongitude(-105.03848001);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.5);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10356939);
        mp.setLongitude(-105.04039303);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.4);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10351296);
        mp.setLongitude(-105.04230549);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.3);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10338509);
        mp.setLongitude(-105.04421091);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.2);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10312949);
        mp.setLongitude(-105.04609453);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10284712);
        mp.setLongitude(-105.04797173);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(349.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10256347);
        mp.setLongitude(-105.04984858);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10227993);
        mp.setLongitude(-105.05172546);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10199146);
        mp.setLongitude(-105.05360102);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10170522);
        mp.setLongitude(-105.05547717);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.6);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10141793);
        mp.setLongitude(-105.05735302);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.5);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10113159);
        mp.setLongitude(-105.05922912);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.4);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10084654);
        mp.setLongitude(-105.06110556);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.3);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10056914);
        mp.setLongitude(-105.06298263);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.2);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10033262);
        mp.setLongitude(-105.06486835);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.1);
        mp.setCommonName("I 80");
        mp.setLatitude(41.1002209);
        mp.setLongitude(-105.06677366);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(348.0);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10021764);
        mp.setLongitude(-105.06868528);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(347.9);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10023417);
        mp.setLongitude(-105.0705969);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(347.8);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10025026);
        mp.setLongitude(-105.07250851);
        mps.add(mp);

        mp = new Milepost();
        mp.setDirection("D");
        mp.setMilepost(347.7);
        mp.setCommonName("I 80");
        mp.setLatitude(41.10027094);
        mp.setLongitude(-105.07442006);
        mps.add(mp);

        return mps;
    }
}