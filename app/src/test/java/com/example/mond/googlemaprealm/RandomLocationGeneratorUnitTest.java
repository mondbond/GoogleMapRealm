package com.example.mond.googlemaprealm;
import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

import com.example.mond.googlemaprealm.data.RandomLocationGenerator;
import com.example.mond.googlemaprealm.model.Marker;
import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;
import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomLocationGenerator.class)
public class RandomLocationGeneratorUnitTest {

    RandomLocationGenerator mRandomLocationGenerator;

    @Before
    public void setupMapPresenterImpl() {
        MockitoAnnotations.initMocks(this);

        mRandomLocationGenerator = new RandomLocationGenerator();
    }

    @Test
    public void generateRandomLocations_isCorrectCountAndCoordinates() {

        String expectedId = "someId";
        int expectedIconType = 2;
        int inputCount = 6;
        int inputRadius = 100;
        LatLng inputLatLng = new LatLng(0, 0);

        String generateLocationMethodName = "generateRandomLocation";
        String generateUUIDMethodName = "generateUUID";
        String generateIconTypeMethodName = "generateIconType";

        double expectedCoordinates = 0.01;
        LatLng expectedLatLng = new LatLng(expectedCoordinates, expectedCoordinates);

        RandomLocationGenerator randomLocationGeneratorSpy = spy(mRandomLocationGenerator);
        final Method methodMock = method(RandomLocationGenerator.class, generateLocationMethodName,
                LatLng.class, int.class);
        try {
            when(randomLocationGeneratorSpy, methodMock).withArguments(inputLatLng, inputRadius)
                    .thenReturn(expectedLatLng);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Method genereteId = method(RandomLocationGenerator.class, generateUUIDMethodName);
        try {
            when(randomLocationGeneratorSpy, genereteId).withNoArguments().thenReturn(expectedId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Method generateIcon = method(RandomLocationGenerator.class, generateIconTypeMethodName);
        try {
            when(randomLocationGeneratorSpy, generateIcon).withNoArguments().thenReturn(expectedIconType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Marker> markers = (ArrayList<Marker>) randomLocationGeneratorSpy
                .generateRandomLocations(inputLatLng, inputRadius, inputCount);

        try {
            verifyPrivate(randomLocationGeneratorSpy, Mockito.times(inputCount)).invoke(methodMock)
                    .withArguments(inputLatLng, inputRadius);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(inputCount, markers.size());
        assertEquals(expectedCoordinates, markers.get(0).getLatitude(), 0);
        assertEquals(expectedCoordinates, markers.get(0).getLongitude(), 0);
        assertEquals(expectedId, markers.get(0).getId());
        assertEquals(expectedIconType, markers.get(0).getIconType());
    }
}
