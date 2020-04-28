package dao;

import org.codehaus.jettison.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MasterDAOTest {

    @Test
    void myTestMethod() {
        assertEquals(1, 1);
    }

    @Test
    public void testEmptyList() {
        List<Object> list = null;

        assertTrue (list.isEmpty());
    }

    public void testAdd(){
        List<Object> list = null;
        list.add("cat");
        assertTrue(list.isEmpty());
    }

}