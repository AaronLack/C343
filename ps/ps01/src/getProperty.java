/**
 * @author Aaron Lack, alack, C343 FA20. Last Edited: 9/2/20.
 * getProperty is problem 1 for the first problem set.
 * I am simply copying and pasting to figure out what VM I have with the getProperty tag
 * And then pasting what I find in the README file.
 */

public class getProperty {
    public static void main(String[] args) {
        System.out.println("java.vm.version is " + System.getProperty("java.vm.version"));
        System.out.println("java.vm.vendor is " + System.getProperty("java.vm.vendor"));
        System.out.println("java.vm.name is " + System.getProperty("java.vm.name"));
        System.out.println("java.vm.specification.version is " + System.getProperty("java.vm.specification.version"));
        System.out.println("java.vm.specification.vendor is " + System.getProperty("java.vm.specification.vendor"));
        System.out.println("java.vm.specification.name is " + System.getProperty("java.vm.specification.name"));
        System.out.println("java.version is " +  System.getProperty("java.version"));
        System.out.println("java.vendor is " + System.getProperty("java.vendor"));
    }
}
