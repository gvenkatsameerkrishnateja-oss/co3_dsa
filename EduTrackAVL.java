class Student {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}

class AVLNode {
    Student student;
    AVLNode left, right;
    int height;

    AVLNode(Student student) {
        this.student = student;
        height = 1;
    }
}

public class EduTrackAVL {
    AVLNode root;

    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, Student student) {
        if (node == null)
            return new AVLNode(student);

        if (student.id < node.student.id)
            node.left = insert(node.left, student);
        else if (student.id > node.student.id)
            node.right = insert(node.right, student);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && student.id < node.left.student.id)
            return rightRotate(node);

        if (balance < -1 && student.id > node.right.student.id)
            return leftRotate(node);

        if (balance > 1 && student.id > node.left.student.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && student.id < node.right.student.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AVLNode search(AVLNode node, int id) {
        if (node == null || node.student.id == id)
            return node;

        if (id < node.student.id)
            return search(node.left, id);

        return search(node.right, id);
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("ID: " + node.student.id +
                    " Name: " + node.student.name +
                    " GPA: " + node.student.gpa);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        EduTrackAVL tree = new EduTrackAVL();

        tree.root = tree.insert(tree.root, new Student(101, "Krishna", 8.5));
        tree.root = tree.insert(tree.root, new Student(105, "Rahul", 7.8));
        tree.root = tree.insert(tree.root, new Student(103, "Teja", 9.1));

        System.out.println("Student Records:");
        tree.inorder(tree.root);

        AVLNode result = tree.search(tree.root, 103);
        if (result != null)
            System.out.println("\nFound: " + result.student.name);
    }
}