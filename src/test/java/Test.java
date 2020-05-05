/*
    author: Timor
    date: 2020/4/5 0005
*/
public class Test {

    @org.junit.Test
    public void test(){
        assert (checkPassword("123456"));
        assert (checkPassword("abcdefg"));
        assert (checkPassword("abc123"));

        assert (!checkPassword("123"));
        assert (!checkPassword("abc"));
        assert (!checkPassword("abc!"));
        assert (checkPassword("1234___!"));
        assert (checkPassword("_+/~!@#$%^&*<>"));
        assert (checkPassword("&*<>,.?':;|"));
        assert (!checkPassword("张三abc123"));
        assert (!checkPassword(""));
        assert (!checkPassword(null));
    }



    public boolean checkPassword(String name){
        if(null == name) return false;
        String regex = "^[a-zA-Z0-9_+/~!@#$%^&*(){}\\[\\]\"<>,.?':;|-]{6,18}$";
        return name.matches(regex);
    }
}
