package data.repositories;

import data.models.TrackingInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrackingInfosTest {
    @Test
    public void testThatNewTrackingInfoRepositoryIsEmpty() {
        TrackingInfos trackingInfos = new TrackingInfos();
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(0, numberOfTrackingInfos);
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanSaveTrackingInfos() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo saved  = trackingInfos.save(trackingInfo);
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(1, numberOfTrackingInfos);
        assertEquals(saved,trackingInfo);
    }

    @Test
    public void testThatNullCantBeSaved(){
        TrackingInfos trackingInfos = new TrackingInfos();
        assertThrows(IllegalArgumentException.class, ()->trackingInfos.save(null));
        assertThrows(IllegalArgumentException.class, ()->trackingInfos.saveAll(null,null,null));
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanSaveMultipleTrackingInfos(){
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo1 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo trackingInfo2 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.saveAll(trackingInfo1, trackingInfo2);
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(2, numberOfTrackingInfos);

    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanFindTrackingInfoById() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo);
        TrackingInfo foundTrackingInfo = trackingInfos.findById(trackingInfo.getTrackingInfoId());
        assertEquals(trackingInfo, foundTrackingInfo);
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanDeleteTrackingInfo() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo);
        trackingInfos.delete(trackingInfo.getTrackingInfoId());
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(0, numberOfTrackingInfos);
    }

    @Test
    public void testThatDeleteThrowsExceptionWithInvalidId(){
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo);
        assertThrows(IllegalArgumentException.class, ()->trackingInfos.delete(-1));
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanDeleteAllTrackingInfos() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo1 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo trackingInfo2 = new TrackingInfo(1,"product",2025,2,11,13,1,0);

        trackingInfos.save(trackingInfo1);
        trackingInfos.save(trackingInfo2);
        trackingInfos.deleteAll();
        assertEquals(0, trackingInfos.count());
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanUpdateTrackingInfo() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo);
        trackingInfo.setInfo("New name");
        trackingInfos.save(trackingInfo);
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(1, numberOfTrackingInfos);
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanDeleteMultipleTrackingInfos() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo1 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo trackingInfo2 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo1);
        trackingInfos.save(trackingInfo2);

        assertEquals(2, trackingInfos.count());
        trackingInfos.deleteAll(trackingInfo1,trackingInfo2);
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(0, numberOfTrackingInfos);
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanDeleteMultipleTrackingInfosById() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo1 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo trackingInfo2 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo1);
        trackingInfos.save(trackingInfo2);

        assertEquals(2, trackingInfos.count());
        trackingInfos.deleteAllById(trackingInfo1.getTrackingInfoId(),trackingInfo2.getTrackingInfoId());
        long numberOfTrackingInfos = trackingInfos.count();
        assertEquals(0, numberOfTrackingInfos);
    }

    @Test
    public void testThatNewTrackingInfoRepositoryCanFindTrackingInfoAllTrackingInfoById() {
        TrackingInfos trackingInfos = new TrackingInfos();
        TrackingInfo trackingInfo1 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        TrackingInfo trackingInfo2 = new TrackingInfo(1,"product",2025,2,11,13,1,0);
        trackingInfos.save(trackingInfo1);
        trackingInfos.save(trackingInfo2);
        ArrayList<TrackingInfo> foundTrackingInfos = trackingInfos.findAllById(trackingInfo1.getTrackingInfoId(),trackingInfo2.getTrackingInfoId());
        assertEquals(2, foundTrackingInfos.size());
    }
}