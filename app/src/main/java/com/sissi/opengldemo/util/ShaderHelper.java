package com.sissi.opengldemo.util;

import android.util.Log;

import static android.opengl.GLES20.*;

public class ShaderHelper {
    private static final String TAG = ShaderHelper.class.getSimpleName();

    public static int compileVertexShader(String shaderCode){
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode){
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode){
        int shaderObjId = glCreateShader(type);
        if (0 == shaderObjId){
            Log.w(TAG, "Could not create new shader.");
            return 0;
        }

        glShaderSource(shaderObjId, shaderCode);
        glCompileShader(shaderObjId);

        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjId, GL_COMPILE_STATUS, compileStatus, 0);

        Log.i(TAG, "Results of compiling source:"+"\n"+shaderCode+"\n:"
                + "status="+compileStatus[0]+"\n"
                +glGetShaderInfoLog(shaderObjId));

        if (0==compileStatus[0]){
            // 编译失败
            glDeleteShader(shaderObjId);
            return 0;
        }

        return shaderObjId;

    }

    public static int linkProgram(int vertextShaderId, int fragmentShaderId){
        final int programObjId = glCreateProgram();
        if (0==programObjId){
            Log.w(TAG, "Could not create new program.");
            return 0;
        }

        glAttachShader(programObjId, vertextShaderId);
        glAttachShader(programObjId, fragmentShaderId);

        glLinkProgram(programObjId);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjId, GL_LINK_STATUS, linkStatus, 0);

        Log.i(TAG, "Results of linking program:"+"\n"
                + "status="+linkStatus[0]+"\n"
                +glGetProgramInfoLog(programObjId));

        if (0==linkStatus[0]){
            // 链接失败
            glDeleteProgram(programObjId);
            return 0;
        }

        return programObjId;

    }


    public static boolean validateProgram(int programObjId){
        glValidateProgram(programObjId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjId, GL_VALIDATE_STATUS, validateStatus, 0);
        Log.i(TAG, "Results of validating program:"+validateStatus[0]
                + "\nLog:"+glGetProgramInfoLog(programObjId));

        return 0 != validateStatus[0];
    }

}
