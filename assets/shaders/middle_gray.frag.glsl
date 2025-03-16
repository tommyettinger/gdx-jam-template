#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying vec2 v_texCoords;
varying LOWP vec4 v_color;
uniform sampler2D u_texture;
void main()
{
   vec4 tgt = texture2D(u_texture, v_texCoords);
   gl_FragColor = clamp(vec4(tgt.rgb * v_color.rgb * 2.0, v_color.a * tgt.a), 0.0, 1.0);
}
