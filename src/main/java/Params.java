package main.java;

/**
 * @author Valentyn Kidruk
 */
public class Params {

    private String inFileName;
    private String outFileName;
    private boolean generate = false;

    static private final String HINT = "Usage:\n"
            + "rain [OPTIONS]\n\n"
            + "Examples:\n"
            + "rain -i input.txt\n"
            + "rain -i input.txt -o output.txt\n"
            + "rain -g -o output.txt\n\n"
            + "  Options:\n"
            + "-i\tinput_file\n"
            + "\t input filename next param expected\n"
            + "-g\tgenerate data\n"
            + "\t \"-i\" will be ignored\n"
            + "-o\toutput_file\n"
            + "\t output filename next param expected\n";

    public String getInFileName() {
        return inFileName;
    }

    public String getOutFileName() {
        return outFileName;
    }

    public boolean isGenerate() {
        return generate;
    }

    /**
     * To parse command line parameters
     * 
     * @param  String[] args - set of parameters
     */
    public void parse(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("Too many arguments\n");
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
            case "-i":
                if (i + 1 < args.length) {
                    inFileName = args[++i];
                } else {
                    throw new Exception("expected filename after \"-i\" param\n");
                }
                break;

            case "-o":
                if (i + 1 < args.length) {
                    outFileName = args[++i];
                } else {
                    throw new Exception("expected filename after \"-o\" param\n");
                }
                break;

            case "-g":
                generate = true;
                break;

            default:
                throw new Exception("Invalid argument \"" + args[i] + "\"\n");
            }
        }

        if (generate && outFileName == null) {
            throw new Exception("expected outfile in case of generate data\n");
        }
        if (generate) {
            inFileName = null;
        }
    }

    public void printHint() {
        System.out.println(HINT);
    }
}
