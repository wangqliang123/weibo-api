package weibo4j.wang;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Some functions for printing.
 *
 * @author Carl
 * @2:46:38 PM @Oct 20, 2009
 * @PrioriEM
 */
public class P {

    /**
     * Print blank line.
     */
    public static void pl() {
        System.out.println();
    }

    /**
     * Print a String
     *
     * @param s
     */
    public static void pl(String s) {
        System.out.println(s);
    }

    /**
     * Print an arraylist.
     * @param list
     */
    public static void pl(ArrayList<String> list) {
        if (list != null) {
            for (String s : list) {
                pl(s);
            }
        }
    }

    /**
     * Print a String.
     *
     * @param s
     */
    public static void p(String s) {
        System.out.print(s);
    }

    /**
     * Print a string in red color
     *
     * @param s
     */
    public static void pr(String s) {
        System.err.println(s);
    }

    /**
     * Print an object.
     *
     * @param s
     */
    public static void pl(Object s) {
        System.out.println(s.toString());
    }

    /**
     * Print an object in red color.
     *
     * @param s
     */
    public static void pr(Object s) {
        System.err.println(s.toString());
    }

    /**
     * Print attributes of a node.
     *
     * @param node
     */
    public static void prAttrs(Node node) {
        NamedNodeMap map = node.getAttributes();
        if (map.getLength() == 0) {
            P.pl("No attributes");
            return;
        }
        P.pl("Attributes - " + map.getLength());
        for (int i = 0; i < map.getLength(); i++) {
            Node t = map.item(i);
            P.pl("\t" + t.getNodeName() + "=" + t.getNodeValue());
        }
    }
}
