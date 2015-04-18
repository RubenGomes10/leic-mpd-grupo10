package pt.isel.mpd.jsonzai;


import org.junit.Test;
import pt.isel.mpd.jsonzai.classesForTest.objectsClasses.TestObjectInternal;
import pt.isel.mpd.jsonzai.classesForTest.objectsClasses.TestObjects;
import pt.isel.mpd.jsonzai.classesForTest.objectsClasses.TestSimpleObject;
import pt.isel.mpd.jsonzai.classesForTest.primitivesClasses.*;
import pt.isel.mpd.jsonzai.objects.MyObject;
import pt.isel.mpd.jsonzai.objects.ObjectWithInternalObject;
import pt.isel.mpd.jsonzai.objects.TestObject;
import pt.isel.mpd.jsonzai.primitives.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class JsonParserTest {

    private static String jsonStr;
    public Primitive primitiveResp;
    public MyObject objectResp;
    public JsonParser parser;

    @Test
    public void testIntInJsonString() throws Exception {

        TestPrimitives<Integer> testInt = new IntTest(10, 20, 30);
        List<Integer> values = getValuesForTestPrimitives(testInt, new PrimitiveInt());

        assertEquals(values.get(0).intValue(),10);
        assertEquals(values.get(1).intValue(),20);
        assertEquals(values.get(2).intValue(),30);
    }

    @Test
    public void test_int_in_json_string_with_spaces() throws Exception {

        TestPrimitives<Integer> testInt = new IntTest(4000, 5000, 4000);
        List<Integer> values =  getValuesForTestWithSpaces(testInt,new PrimitiveInt());

        assertEquals(values.get(0).intValue(),4000);
        assertEquals(values.get(1).intValue(),5000);
        assertEquals(values.get(2).intValue(),4000);
    }

    @Test
    public void test_char_in_json_String() throws Exception {

        TestPrimitives<Character> testChar = new CharTest('A','B','C');
        List<Character> values = getValuesForTestPrimitives(testChar, new PrimitiveChar());

        assertEquals(values.get(0).charValue(),'A');
        assertEquals(values.get(1).charValue(),'B');
        assertEquals(values.get(2).charValue(),'C');
    }

    @Test
    public void test_boolean_in_json_string() throws Exception {
        TestPrimitives<Boolean> testBoolean = new BooleanTest(false,true,true);
        List<Boolean> values = getValuesForTestPrimitives(testBoolean, new PrimitiveBoolean());

        assertEquals(values.get(0).booleanValue(),false);
        assertEquals(values.get(1).booleanValue(),true);
        assertEquals(values.get(2).booleanValue(),true);
    }

    @Test
    public void test_String_withSpaces_and_comma_in_double_quotes() throws Exception {
        TestPrimitives<String> testString = new StringTest("ISEL, Lisboa","Murteira, Alcobaça", "Parque das Nações");
        List<String> values = getValuesForTestPrimitives(testString, new PrimitiveString());

        assertEquals(values.get(0),"ISEL, Lisboa");
        assertEquals(values.get(1),"Murteira, Alcobaça");
        assertEquals(values.get(2),"Parque das Nações");
    }

    @Test
    public void test_Date_in_json_string() throws Exception {
        DateFormat dateFormat = new JsonParser().getDateFormat();
        TestPrimitives<Date> testDate = new DateTest("2015-04-05", "2013-06-28", "2000-10-15");
        List<Date> values = getValuesForTestPrimitives(testDate, new PrimitiveDate());

        assertEquals(values.get(0), dateFormat.parse("2015-04-05"));
        assertEquals(values.get(1), dateFormat.parse("2013-06-28"));
        assertEquals(values.get(2), dateFormat.parse("2000-10-15"));
    }

    @Test
    public void test_simple_object_with_mixed_primitives() throws Exception {
        TestObjects simpleObjectTest = new TestSimpleObject(10,'R',true,"Rúben Gomes");
        List<Object> values = getValuesForTestObjects(simpleObjectTest, new TestObject());

        assertEquals(values.get(0),10);
        assertEquals(values.get(1),'R');
        assertEquals(values.get(2),true);
        assertEquals(values.get(3),"Rúben Gomes");
    }

    @Test
    public void test_internalObject_with_mixed_primitives() throws Exception {
        TestObjects internalObjectTest = new TestObjectInternal("LEIC","Rúben Gomes",39367,true);
        List<Object> values = getValuesForTestObjects(internalObjectTest,new ObjectWithInternalObject());

        assertEquals(values.get(0),"LEIC");
        assertEquals(values.get(1),"Rúben Gomes");
        assertEquals(values.get(2),39367);
        assertEquals(values.get(3),true);
    }

    private List<Object> getValuesForTestObjects(TestObjects simpleObjectTest, MyObject testObject) throws Exception {
        jsonStr = simpleObjectTest.getString();
        parser = new JsonParser();
        objectResp = parser.toObject(jsonStr,testObject.getClass());
        return objectResp.getValues();
    }

    private <T>List<T> getValuesForTestPrimitives(TestPrimitives<T> src, Primitive primitive) throws Exception {
        jsonStr = src.getString();
        parser = new JsonParser();
        primitiveResp = parser.toObject(jsonStr,primitive.getClass());
        return primitiveResp.getValues();
    }

    private <T>List<T> getValuesForTestWithSpaces(TestPrimitives<T> src,Primitive primitive) throws Exception {
        jsonStr = src.getStringWithSpaces();
        parser = new JsonParser();
        primitiveResp = parser.toObject(jsonStr, primitive.getClass());
        return primitiveResp.getValues();
    }
}