# proguard github example for application
# https://github.com/Guardsquare/proguard/blob/master/examples/gradle/applications.gradle
-keepattributes '*Annotation*'

-keepclasseswithmembers public class * { public static void main(java.lang.String[]); }

-keep public class org.lwjgl.system.** { *; }
# You will need the next line if you use scene2d for UI or gameplay
-keep public class com.badlogic.gdx.scenes.scene2d.** { *; }

-keepclasseswithmembernames class * { native <methods>; }

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
         static final long serialVersionUID;
         static final java.io.ObjectStreamField[] serialPersistentFields;
         private void writeObject(java.io.ObjectOutputStream);
         private void readObject(java.io.ObjectInputStream);
         java.lang.Object writeReplace();
         java.lang.Object readResolve();
     }

-forceprocessing
#-classobfuscationdictionary 'obfuscationClassNames.txt'
-ignorewarnings
-overloadaggressively
-mergeinterfacesaggressively
-repackageclasses ''
-allowaccessmodification

# FIELD ISSUE NPE
-optimizations !field/propagation/value

# LAMBDA FIX
-keepclassmembernames class * {
    private static synthetic *** lambda$*(...);
}

###### PROGUARD ANNOTATIONS END #####
-optimizationpasses 5


# Should be removed to test auto-configuration, when possible.

# Gamut inline data fix
-optimizations !code/simplification/string
