package pt.isel.mpd.jsonzai.classesForTest.objectsClasses;


public  class TestObjectInternal extends TestObjects {

    public TestObjectInternal(String course,String name,int number,boolean isStudent) {
        super(course,name,number,isStudent);
    }

    @Override
    public String getString() {
        return "{ \n" +
                "\"course\" = "+ "\""+values.get(0)+"\"" +", \n \t" +
                "\"person\" = { \n \t" +
                              "\"name\" = "+"\""+ values.get(1) +"\"" +", \n \t" +
                              "\"number\" = "+ values.get(2) +", \n \t" +
                              "\"isStudent\" = "+ values.get(3) +"\n \t" +
                             "} \n"+
                "}";
    }

    @Override
    public String getStringWithSpaces() {
        return null;
    }
}
