import java.util.*;

public class RecursiveTreePreview {

    static class Folder {
        String name;
        List<Object> contents; // can be File or Folder

        Folder(String name) {
            this.name = name;
            contents = new ArrayList<>();
        }
    }

    static class File {
        String name;
        File(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Object obj) {
        if (obj instanceof File) return 1;
        if (obj instanceof Folder folder) {
            int sum = 0;
            for (Object o : folder.contents)
                sum += countFiles(o);
            return sum;
        }
        return 0;
    }

    public static void printMenu(Folder f, int level) {
        System.out.println("  ".repeat(level) + "- " + f.name);
        for (Object o : f.contents) {
            if (o instanceof File file)
                System.out.println("  ".repeat(level + 1) + "* " + file.name);
            else if (o instanceof Folder sub)
                printMenu(sub, level + 1);
        }
    }

    public static List<Object> flatten(Object obj) {
        List<Object> result = new ArrayList<>();
        if (obj instanceof File) result.add(obj);
        else if (obj instanceof Folder folder)
            for (Object o : folder.contents)
                result.addAll(flatten(o));
        return result;
    }

    public static int maxDepth(Object obj) {
        if (obj instanceof File) return 1;
        if (obj instanceof Folder folder) {
            int depth = 0;
            for (Object o : folder.contents)
                depth = Math.max(depth, maxDepth(o));
            return depth + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder docs = new Folder("docs");
        docs.contents.add(new File("a.txt"));
        docs.contents.add(new File("b.txt"));
        Folder images = new Folder("images");
        images.contents.add(new File("pic.jpg"));
        root.contents.add(docs);
        root.contents.add(images);
        root.contents.add(new File("readme.md"));

        System.out.println("Total files: " + countFiles(root));
        System.out.println("Menu:");
        printMenu(root, 0);
        System.out.println("Flattened: " + flatten(root).size() + " items");
        System.out.println("Max depth: " + maxDepth(root));
    }
}
