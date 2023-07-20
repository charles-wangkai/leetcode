import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class FileSystem {
  private Component root = new Directory(null);

  public List<String> ls(String path) {
    Component component = navigateTo(path, false);

    List<String> result = new ArrayList<>();
    if (component.isFileOrDirectory()) {
      result.add(component.getName());
    } else {
      result.addAll(((Directory) component).getChildren().keySet());
    }
    return result;
  }

  public void mkdir(String path) {
    navigateTo(path, false);
  }

  public void addContentToFile(String filePath, String content) {
    ((File) navigateTo(filePath, true)).getContent().append(content);
  }

  public String readContentFromFile(String filePath) {
    return ((File) navigateTo(filePath, true)).getContent().toString();
  }

  private Component navigateTo(String path, boolean createFileOrDirectoryIfNotExist) {
    Component component = root;
    for (String name : path.split("/")) {
      if (name.isEmpty()) {
        continue;
      }

      SortedMap<String, Component> children = ((Directory) component).getChildren();
      if (!children.containsKey(name)) {
        if (createFileOrDirectoryIfNotExist) {
          children.put(name, new File(name));
        } else {
          children.put(name, new Directory(name));
        }
      }
      component = children.get(name);
    }
    return component;
  }
}

abstract class Component {
  private String name;
  private boolean fileOrDirectory;

  Component(String name, boolean fileOrDirectory) {
    this.name = name;
    this.fileOrDirectory = fileOrDirectory;
  }

  public String getName() {
    return name;
  }

  public boolean isFileOrDirectory() {
    return fileOrDirectory;
  }
}

class File extends Component {
  private StringBuilder content = new StringBuilder();

  File(String name) {
    super(name, true);
  }

  public StringBuilder getContent() {
    return content;
  }
}

class Directory extends Component {
  private SortedMap<String, Component> children = new TreeMap<>();

  Directory(String name) {
    super(name, false);
  }

  public SortedMap<String, Component> getChildren() {
    return children;
  }
}

// Your FileSystem object will be instantiated and called as such:
// FileSystem obj = new FileSystem();
// List<String> param_1 = obj.ls(path);
// obj.mkdir(path);
// obj.addContentToFile(filePath,content);
// String param_4 = obj.readContentFromFile(filePath);
