#version 330 core

uniform mat4 mlMat, vwMat, prMat;

layout (location = 0) in vec2 vertex;
layout (location = 2) in vec2 inTexCoord;

out vec2 texCoord;

void main() {
  gl_Position = vec4(vertex, 1.0, 1.0) * mlMat * vwMat * prMat;
  texCoord = inTexCoord;
}
