package com.example.mond.googlemaprealm;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.example.mond.googlemaprealm.data.RandomLocationGenerator;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomLocationGenerator.class)
public class RandomLocationGeneratorUnitTest {

    RandomLocationGenerator mTest;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mTest = new RandomLocationGenerator();
    }

    @Test
    public void generateRandomLocations_isCorrect() {
        int count = 6;
        double radius = 100;
        double distancePerDegree = 112;
        double radiusInDegrees = radius / distancePerDegree;

//         final String modifyDataMethodName = "modifyData";
//        final byte[] expectedBinaryData = new byte[] { 42 };
//        final String expectedDataId = "id";
//
//         Mock only the modifyData method
//        DataService tested = createPartialMock(DataService.class, modifyDataMethodName);
//
//         Expect the private method call to "modifyData"
//        expectPrivate(tested, modifyDataMethodName, expectedDataId,
//                expectedBinaryData).andReturn(true);
//
//        replay(tested);
//
//        assertTrue(tested.replaceData(expectedDataId, expectedBinaryData));
//
//        verify(tested);



        ArrayList<Marker> markers = (ArrayList<Marker>) mTest.generateRandomLocations(new LatLng(0,0), 100, 6);
        assertEquals(count, markers.size());

        for(Marker item: markers) {
            System.out.print(String.valueOf(radiusInDegrees));

            assertTrue(radiusInDegrees >= item.getLatitude());
            assertTrue(radiusInDegrees >= item.getLongitude());
        }
    }
}
