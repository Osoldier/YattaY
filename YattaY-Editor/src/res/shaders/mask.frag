#version 330 core

uniform sampler2D tex;
uniform int LEFT_OFFSET;

in vec2 texCoord;

out vec4 color;

void main() {
  color = texture(tex, texCoord);
  if(gl_FragCoord.x < LEFT_OFFSET) {
	discard;
  }
}
