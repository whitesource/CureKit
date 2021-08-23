package io.whitesource.cure;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Remediation Wrapper class of ObjectInputStream written by WhiteSource with the community ❤.
 * This class should replace your usage of ObjectInputStream within your code, to verify the deserialized class is of an approved type.
 */
public class SecureObjectInputStream extends ObjectInputStream {

    private final Set<String> classesWhitelist;
    private final Set<String> packagesWhitelist = new HashSet<>();

    /**
     * @param inputStream     to deserialize.
     * @param validClassNames classes whitelist.
     *                        Beware not to put in your whitelist popular gadget classes that allow remote code execution during their deserialization process.
     * @throws IOException because ObjectInputStream's constructor might throw it.
     */
    public SecureObjectInputStream(InputStream inputStream, Set<String> validClassNames) throws IOException {
        super(inputStream);
        this.classesWhitelist = validClassNames;
    }

    /**
     * @param inputStream     to deserialize.
     * @param validClassNames classes whitelist.
     *                        Beware not to put in your whitelist popular gadget classes that allow remote code execution during their deserialization process.
     * @param validPackages   package whitelist.
     *                        Be as specific as possible with requested whitelist packages, because any of their subpackages will be cleared for deserialization.
     * @throws IOException because ObjectInputStream's constructor might throw it.
     */
    public SecureObjectInputStream(InputStream inputStream, Set<String> validClassNames, Set<String> validPackages) throws IOException {
        super(inputStream);
        this.classesWhitelist = validClassNames;
        this.packagesWhitelist.addAll(validPackages);
    }

    /**
     * Overriding a method that is called before the deserialization itself, and checking if the provided class is of an intended type.
     *
     * @param classToDeserialize from InputStream.
     * @return deserialized class.
     * @throws IOException            when rejecting a deserialization attempt or error in ObjectInputStream.resolveClass.
     * @throws ClassNotFoundException because ObjectInputStream.resolveClass might throw it.
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass classToDeserialize) throws IOException, ClassNotFoundException {
        String className = classToDeserialize.getName();
        if (classesWhitelist.contains(className) || isSubpackage(className)) {
            return super.resolveClass(classToDeserialize);
        }
        throw new InvalidClassException("Unauthorized deserialization attempt detected for class " + classToDeserialize.getName());
    }

    private boolean isSubpackage(String className) {
        String packageName = FilenameUtils.removeExtension(className);
        for (String pack : packagesWhitelist) {
            if (packageName.startsWith(pack)) {
                return true;
            }
        }
        return false;
    }
}
