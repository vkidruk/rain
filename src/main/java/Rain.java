package main.java;

/**
 * @author Valentyn Kidruk
 */
public class Rain {

    public static void main(String[] args) {

        Params params = new Params();
        try {
            params.parse(args);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            params.printHint();
            System.exit(1);
            return;
        }

        Data data = new Data(params.getInFileName(), params.getOutFileName());
        try {
            if (params.isGenerate()) {
                data.generate();
            } else {
                data.process();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return;
        }
    }
}
