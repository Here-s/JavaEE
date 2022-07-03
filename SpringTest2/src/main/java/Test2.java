import java.beans.Introspector;

public class Test2 {

    public static void main(String[] args) {
        String className = "APIUserContoller";
        System.out.println(Introspector.decapitalize(className));
    }
}
