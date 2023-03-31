package com.xixi.plugin.asm

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter

/**
 * Author:xishuang
 * Date:2018.01.08
 * Des:修改字节码
 */
class AutoModify {

    static byte[] modifyClasses(String className, byte[] srcByteCode) {
        byte[] classBytesCode = null
        try {
            if ((!className.contains(".") && !className.contains('$')) || className.contains("META-INF")) {
                println("当前属于要扫描的不属于类，不执行---->>>className = $className")
                return
            }
            if (className.startsWith('kotlin') || className.contains("jetbrains") || className.contains("java") || className.contains("javax")) {
                println("当前属于系统类，不执行---->>>className = $className")
                return
            }
            if (className.contains("com.xixi.plugin")){
                println("插件所属跳过不执行------>>>>className = $className")
                return
            }
            if (className.contains('intellij') || className.contains('jetbrains') ){
                println("包含系统包名的不执行------>>>>className = $className")
                return
            }
            println("需要插桩的--------->>>>ClassName = $className")
            classBytesCode = modifyClass(srcByteCode)
            //调试模式下再遍历一遍看修改的方法情况
//            if (Logger.isDebug()) {
//                seeModifyMethod(classBytesCode)
//            }
            return classBytesCode
        } catch (Exception e) {
            e.printStackTrace()
        }
        if (classBytesCode == null) {
            classBytesCode = srcByteCode
        }
        return classBytesCode
    }
    /**
     * 真正修改类中方法字节码
     */
    private static byte[] modifyClass(byte[] srcClass) throws IOException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS)
        ClassVisitor adapter = new AutoClassVisitor(classWriter)
        ClassReader cr = new ClassReader(srcClass)
        cr.accept(adapter, ClassReader.EXPAND_FRAMES)
        return classWriter.toByteArray()
    }
    /**
     * 查看修改字节码后的方法
     */
    private static void seeModifyMethod(byte[] srcClass) throws IOException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS)
        ClassVisitor visitor = new AutoClassVisitor(classWriter)
        visitor.seeModifyMethod = true
        ClassReader cr = new ClassReader(srcClass)
        cr.accept(visitor, ClassReader.EXPAND_FRAMES)
    }
}