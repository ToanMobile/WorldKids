# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Repackage classes into the top-level.
-repackageclasses

-keep class kotlin.Metadata


#--------------- Begin : okhttp3 -----------
# https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier
### Other
-dontwarn com.google.errorprone.annotations.*
-dontwarn com.squareup.okhttp.**
#--------------- End : okhttp3 -----------

#--------------- begin : Okio ----------
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-dontwarn com.squareup.**
-keep public class java.nio.* { *; }
##---------------End: Okio  ----------

#--------------- begin : Retrofit ----------
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep class retrofit2.Response { *; }
 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
##---------------End: Retrofit  ----------

#--------------- begin : Stetho ----------
-keep class com.facebook.stetho.** { *; }
-keep class com.uphyca.** { *; }
-dontwarn com.facebook.stetho.**
##---------------End: Stetho  ----------

#--------------- begin : Leakcanary ----------
# A ContentProvider that gets created by Android on startup
-keep class leakcanary.internal.AppWatcherInstaller { <init>(); }
# KeyedWeakReference is looked up in the hprof file
-keep class leakcanary.KeyedWeakReference { *; }
-keep class org.eclipse.mat.** { *; }
-keep class com.squareup.leakcanary.** { *; }
##---------------End: Leakcanary  ----------

#--------------- begin : Encrypted ----------
-keepclassmembers class * extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite {
  <fields>;
}
##---------------End: Encrypted  ----------

#--------------- begin : Whispersystems ----------
-keepnames class org.whispersystems.curve25519.NativeCurve25519Provider {}
-keepnames class org.whispersystems.curve25519.JavaCurve25519Provider {}
-keepnames class org.whispersystems.curve25519.J2meCurve25519Provider {}
-keepnames class org.whispersystems.curve25519.OpportunisticCurve25519Provider {}
-keep class org.whispersystems.curve25519.NativeCurve25519Provider {}
-keep class org.whispersystems.curve25519.JavaCurve25519Provider {}
-keep class org.whispersystems.curve25519.J2meCurve25519Provider {}
-keep class org.whispersystems.curve25519.OpportunisticCurve25519Provider {}
##---------------End: Whispersystems  ----------

#--------------- Begin : Encrypted -----------
-keep class androidx.core.app.CoreComponentFactory { *; }
-dontwarn androidx.core.app.CoreComponentFactory.**
-keep class * extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite { *; }
-dontwarn org.conscrypt.**
-keepclassmembers class * extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite {
  <fields>;
}
#--------------- End : Encrypted -----------

#--------------- Begin : WebRTC -----------
-keep class org.webrtc.** { *; }
-keep class org.appspot.apprtc.** { *; }
-keep interface org.webrtc.** { *; }
-keep class de.tavendo.autobahn.** { *; }
-dontwarn org.webrtc.voiceengine.WebRtcAudioTrack
-keep class com.cloudwebrtc.webrtc.** { *; }
-keepclasseswithmembernames class * { native <methods>; }
-dontwarn org.chromium.build.BuildHooksAndroid
#--------------- End : WebRTC -----------

#--------------- Begin : Kotlin Coroutine -----------
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}
# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}
# Same story for the standard library's SafeContinuation that also uses AtomicReferenceFieldUpdater
-keepclassmembernames class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}
# https://github.com/Kotlin/kotlinx.atomicfu/issues/57
-dontwarn kotlinx.atomicfu.**
-dontwarn kotlinx.coroutines.flow.**
# kotlinx-coroutines-core is used as a Java agent, so these are not needed in contexts where ProGuard is used.
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn sun.misc.SignalHandler
-dontwarn java.lang.instrument.Instrumentation
-dontwarn sun.misc.Signal
#--------------- End : Kotlin Coroutine -----------

#--------------- Start : Kotlinx serialization -----------
# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}

# Keep `serializer()` on companion objects (both default and named) of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}
-dontnote kotlinx.serialization.**
# @Serializable and @Polymorphic are used at runtime for polymorphic serialization.
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keepattributes InnerClasses # Needed for `getDeclaredClasses`.

-keepnames class <1>$$serializer { # -keepnames suffices; class is kept when serializer() is kept.
    static <1>$$serializer INSTANCE;
}
-if @kotlinx.serialization.Serializable class **
-keep, allowshrinking, allowoptimization, allowobfuscation, allowaccessmodification class <1>
# Keep both serializer and serializable classes to save the attribute InnerClasses
# Application rules
-dontwarn kotlinx.serialization.internal.ClassValueReferences
# Change here com.yourcompany.yourpackage
-keepclassmembers @kotlinx.serialization.Serializable class com.app.worldkids.model.** {
    # lookup for plugin generated serializable classes
    *** Companion;
    # lookup for serializable objects
    *** INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}
-keepclassmembers @kotlinx.serialization.Serializable class ** {
    *** Companion;
}
# lookup for plugin generated serializable classes
-if @kotlinx.serialization.Serializable class com.app.worldkids.model.**
-keepclassmembers class com.app.worldkids.model.<1>$Companion {
    kotlinx.serialization.KSerializer serializer(...);
}

# Serialization supports named companions but for such classes it is necessary to add an additional rule.
# This rule keeps serializer and serializable class from obfuscation. Therefore, it is recommended not to use wildcards in it, but to write rules for each such class.
-keepattributes InnerClasses # Needed for `getDeclaredClasses`.
-keep class com.app.worldkids.model.SerializableClassWithNamedCompanion$$serializer {
    *** INSTANCE;
}
#App Hura TODO
-keep,includedescriptorclasses class com.app.worldkids.model.**$$serializer { *; }
-keepclassmembers class com.app.worldkids.model.** {
    *** Companion;
}
-keepclasseswithmembers class com.app.worldkids.model.** {
    kotlinx.serialization.KSerializer serializer(...);
}
#--------------- End : Kotlinx serialization -----------

#DataStore
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields>;
}
-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }
#--------------- begin : Libs ----------
-keep class org.joda.time.** { *; }
-keep class com.skydoves.sandwich.** { *; }
-keepnames class com.skydoves.sandwich.** { *; }
-keep class com.caverock.androidsvg.** { *; }
-keepnames class com.caverock.androidsvg.** { *; }
-keep class com.canhub.cropper.** { *; }
-keepnames class com.canhub.cropper.** { *; }
-keep class io.github.crow_misia.sdp.** { *; }
-keepnames class io.github.crow_misia.sdp.** { *; }
-keep class me.saket.cascade.** { *; }
-keep class com.github.idanatz.** { *; }
-keep,allowoptimization class com.google.android.libraries.maps.** { *; }
##---------------End: Libs  ----------

# Livekit Serialization Proguard Rules
########################################
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt # core serialization annotations

# kotlinx-serialization-json specific. Add this if you have java.lang.NoClassDefFoundError kotlinx.serialization.json.JsonObjectSerializer
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep,includedescriptorclasses class io.livekit.android.**$$serializer { *; }
-keepclassmembers class io.livekit.android.** {
    *** Companion;
}
-keepclasseswithmembers class io.livekit.android.** {
    kotlinx.serialization.KSerializer serializer(...);
}
# Livekit Serialization Proguard Rules
#Google
-keep class com.google.** { *; }
-keepnames class com.google.** { *; }
-keep class org.** { *; }
-keepnames class org.** { *; }
-keep class android.** { *; }
-keepnames class android.** { *; }
-keep class androidx.** { *; }
-keepnames class androidx.** { *; }
-keep class java.** { *; }
-keepnames class java.** { *; }
-keep class io.** { *; }
-keepnames class io.** { *; }
#Google

#Ktor
-keep class io.ktor.server.netty.EngineMain { *; }
-keep class io.ktor.server.config.HoconConfigLoader { *; }
-keep class com.app.worldkids.AppKt { *; }
-keep class kotlin.reflect.jvm.internal.** { *; }
-keep class kotlin.text.RegexOption { *; }

-keep class io.ktor.** { *; }
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.atomicfu.**
-dontwarn io.netty.**
-dontwarn com.typesafe.**
-dontwarn org.slf4j.**