#version 330 core

uniform mat4 vwMat, prMat;
uniform int blockSize;
uniform float atlasSize;

layout (location = 0) in vec2 vertex;
layout (location = 2) in vec2 inTexCoord;
layout (location = 3) in vec2 iPosition;
layout (location = 4) in vec2 iTexture;

out vec2 texCoord;

void main() {
  gl_Position = vec4((vertex * blockSize)+iPosition, 0.0, 1.0) * vwMat * prMat;
  texCoord = (inTexCoord / atlasSize)+iTexture;
}
