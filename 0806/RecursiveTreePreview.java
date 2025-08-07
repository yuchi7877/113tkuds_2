public class RecursiveTreePreview {
    static class Folder {
        String name;
        List<Folder> subfolders = new ArrayList<>();
        List<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }

        void addSubfolder(Folder folder) {
            subfolders.add(folder);
        }

        void addFile(String file) {
            files.add(file);
        }
    }

    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subfolders) {
            count += countFiles(sub);
        }
        return count;
    }
    static class MenuItem {
        String title;
        List<MenuItem> children = new ArrayList<>();

        MenuItem(String title) {
            this.title = title;
        }

        void addChild(MenuItem item) {
            children.add(item);
        }
    }

    public static void printMenu(MenuItem menu, int depth) {
        System.out.println("  ".repeat(depth) + "- " + menu.title);
        for (MenuItem child : menu.children) {
            printMenu(child, depth + 1);
        }
    }
    public static List<Integer> flattenList(List<Object> nestedList) {
        List<Integer> result = new ArrayList<>();
        for (Object item : nestedList) {
            if (item instanceof Integer) {
                result.add((Integer) item);
            } else if (item instanceof List) {
                result.addAll(flattenList((List<Object>) item));
            }
        }
        return result;
    }
    public static int maxDepth(List<Object> nestedList) {
        int depth = 1;
        for (Object item : nestedList) {
            if (item instanceof List) {
                depth = Math.max(depth, 1 + maxDepth((List<Object>) item));
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        Folder root = new Folder("Root");
        Folder docs = new Folder("Documents");
        Folder pics = new Folder("Pictures");
        root.addSubfolder(docs);
        root.addSubfolder(pics);
        docs.addFile("resume.pdf");
        docs.addFile("report.docx");
        pics.addFile("photo.jpg");

        System.out.println("總檔案數：" + countFiles(root)); 

        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem fileMenu = new MenuItem("檔案");
        MenuItem editMenu = new MenuItem("編輯");
        MenuItem openItem = new MenuItem("開啟");
        MenuItem saveItem = new MenuItem("儲存");
        fileMenu.addChild(openItem);
        fileMenu.addChild(saveItem);
        mainMenu.addChild(fileMenu);
        mainMenu.addChild(editMenu);

        System.out.println("\n選單結構：");
        printMenu(mainMenu, 0);

        List<Object> nested = Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(Arrays.asList(4), 5));
        System.out.println("\n展平結果：" + flattenList(nested)); 
        System.out.println("最大深度：" + maxDepth(nested)); 
    }
}
