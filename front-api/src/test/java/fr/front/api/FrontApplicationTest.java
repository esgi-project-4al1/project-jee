package fr.front.api;

import jakarta.ws.rs.ApplicationPath;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

class FrontApplicationTest {

    @Test
    public void testApplicationPathAnnotation() {

        Annotation[] annotations = FrontApplication.class.getAnnotationsByType(ApplicationPath.class);
        assertTrue(annotations.length > 0, "L'annotation @ApplicationPath est manquante.");

        ApplicationPath applicationPath = (ApplicationPath) annotations[0];
        assertEquals("/api", applicationPath.value(), "Le chemin de l'annotation @ApplicationPath est incorrect.");
    }

    @Test
    public void testFrontApplicationInstantiation() {

        FrontApplication app = new FrontApplication();
        assertNotNull(app, "L'instance de FrontApplication ne doit pas être nulle.");
    }
}