package com.ckinan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code snippet to find elements within a html (from file and url) using XPath.
 *
 * It uses a third party library called XSoup. Links:
 * - mvn repository: https://mvnrepository.com/artifact/us.codecraft/xsoup
 * - github repository: https://github.com/code4craft/xsoup
 *
 * This example reads a list of blog posts using a fixed XPath expression and prints the results in the console.
 * */
public class App {

    public static final String XPATH_BLOG_POST = "//div[contains(@class, 'leading-snug')]";

    /**
     * The method main of the program. It starts and orchestrates the process.
     * It gets instances of Documents from specific sources and sends them to read specific elements
     * by xpath expression.
     *
     * It retrieve documents from:
     * - Files (by specifying the full path)
     * - URL (of the website to be parsed)
     *
     * @param  args
     *         Arguments of our application. Won't be used at all

     * @throws  java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Calling the static methods that will get an instance of the HTML document and then evaluate
        // the XPath expression to get a list of Blog Posts. First with a HTML file, then do the same with the website
        // URL directly.
        Document doc1 = App.getDocumentFromFile("src/main/resources/blog.html");
        List<String> postsFromFile = App.readDocument(doc1, App.XPATH_BLOG_POST);
        System.out.println(postsFromFile);

        Document doc2 = App.getDocumentFromUrl("https://ckinan.com/blog");
        List<String> postsFromUrl = App.readDocument(doc2, App.XPATH_BLOG_POST);
        System.out.println(postsFromUrl);
    }

    /**
     * Read the content file of the given full path using "collect" from Stream Java 8+.
     *
     * Refs:
     * - https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html#get-java.lang.String-java.lang.String...-
     * - https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#lines-java.nio.file.Path-
     * - https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-
     *
     * Then it uses Jsoup to parse the content file into a Document instance.
     *
     * @param  fullPath
     *         The full path of the file to be read and parsed using Jsoup.

     * @throws  java.io.IOException
     *
     * @return  The parsed document which contains all the elements of the HTML file
     */
    public static Document getDocumentFromFile(String fullPath) throws IOException {
        System.out.println("Reading document from file: " + fullPath);
        // Read the content file using Java 8+ Streams.
        String html = Files.lines(Paths.get(fullPath)).collect(Collectors.joining(System.lineSeparator()));
        return Jsoup.parse(html);
    }

    /**
     * Read the content of the given website url using Jsoup
     *
     * @param  url
     *         The URL of the website HTML to be read and parsed using Jsoup.
     *
     * @throws  java.io.IOException
     *
     * @return  The parsed document which contains all the elements of the HTML file
     */
    public static Document getDocumentFromUrl(String url) throws IOException {
        System.out.println("Reading document from url: " + url);
        return Jsoup.connect(url).get();
    }

    /**
     * Evaluates the given XPath expression on the given Document which is limited to assume that it's a list of
     * Elements right now. Returns results of the XPath evaluation.
     *
     * @param  doc
     *         The URL of the website HTML to be read and parsed using Jsoup.
     *
     * @param  xpath
     *         The URL of the website HTML to be read and parsed using Jsoup.
     *
     * @throws  java.io.IOException
     *
     * @return  The list of string values resulted from the XPath expression.
     */
    public static List<String> readDocument(Document doc, String xpath) {
        List<String> result = new ArrayList<>();

        // It first extract the Element instances of the html content using the given Document and the XPath expression
        // Note: Xsoup uses Jsoup as the HTML parser. Xsoup "evaluates" a Document which is an instance variable created
        //       by Jsoup
        List<Element> elements =
                Xsoup.compile(xpath).evaluate(doc).getElements();

        for(Element e: elements) {
            // Each Element object has multiple properties available to be extracted, for example the HTML tag name.
            // For this code snipped, we are interested into extract the text of the element.
            // Example: <p>Hello world</p> -> e.text() would return "Hello world"
            result.add(e.text());
        }

        return result;
    }

}
