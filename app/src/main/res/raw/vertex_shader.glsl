

attribute vec4 a_Position;
attribute vec4 a_Color;
varying vec4 v_Color;

uniform mat4 u_Matrix;

void main(){
    v_Color = a_Color;
    gl_Position = u_Matrix*a_Position; // 矩阵乘以向量=>坐标变换
    gl_PointSize = 10.0;
}