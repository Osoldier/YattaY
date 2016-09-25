#version 330 core

uniform mat4 prMat, mlMat;

layout (location = 0) in vec2 vertex;
layout (location = 2) in vec2 inTexCoord;

out vec2 texCoord;

void main() {
	gl_Position = vec4(vertex, 0.0, 1.0) * mlMat * prMat;
	texCoord = inTexCoord;
}
