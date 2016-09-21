#version 330 core

uniform mat4 vwMat, prMat;
uniform int size;

layout (location = 0) in vec2 vertex;
layout (location = 2) in vec2 inTexCoord;

out vec2 texCoord;

void main() {
  gl_Position = vec4(vertex * size, 0.0, 1.0) * vwMat * prMat;
  texCoord = inTexCoord;
}
