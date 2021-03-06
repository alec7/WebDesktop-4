PGDMP         8                u            BambuWS    9.3.16    9.3.16 ~   X
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            Y
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            Z
           1262    18899    BambuWS    DATABASE     �   CREATE DATABASE "BambuWS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
    DROP DATABASE "BambuWS";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            [
           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    7            \
           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    7                        3079    11750    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            ]
           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    18900 
   tb_acuerdo    TABLE       CREATE TABLE tb_acuerdo (
    codigo character varying(5) NOT NULL,
    nombre_empresa character varying(45),
    tipo_acuerdo character varying(5),
    codigo_organizacion character varying(10),
    status character varying(8),
    fecha date,
    observacion character varying(500)
);
    DROP TABLE public.tb_acuerdo;
       public         postgres    false    7            �            1259    18906 	   tb_agenda    TABLE     �   CREATE TABLE tb_agenda (
    codigo character varying(5) NOT NULL,
    status character varying(8),
    descripcion character varying(45),
    codigo_dia character varying(5)
);
    DROP TABLE public.tb_agenda;
       public         postgres    false    7            �            1259    18909    tb_antecedente    TABLE     �   CREATE TABLE tb_antecedente (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 "   DROP TABLE public.tb_antecedente;
       public         postgres    false    7            �            1259    18912    tb_antecedente_cliente    TABLE     �   CREATE TABLE tb_antecedente_cliente (
    codigo_antecedente character varying(5) NOT NULL,
    codigo_cliente character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 *   DROP TABLE public.tb_antecedente_cliente;
       public         postgres    false    7            �            1259    18915    tb_antecedente_servicio    TABLE     �   CREATE TABLE tb_antecedente_servicio (
    codigo_maestro character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 +   DROP TABLE public.tb_antecedente_servicio;
       public         postgres    false    7            �            1259    18918 	   tb_avance    TABLE     �   CREATE TABLE tb_avance (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
    DROP TABLE public.tb_avance;
       public         postgres    false    7            �            1259    18921    tb_avance_servicio    TABLE     �   CREATE TABLE tb_avance_servicio (
    codigo_maestro character varying(5),
    codigo character varying(5) NOT NULL,
    codigo_servicio character varying(5),
    status character varying(8)
);
 &   DROP TABLE public.tb_avance_servicio;
       public         postgres    false    7            �            1259    18924 	   tb_bloque    TABLE     �   CREATE TABLE tb_bloque (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    hora_inicio timestamp without time zone,
    hora_fin timestamp without time zone,
    status character varying(8)
);
    DROP TABLE public.tb_bloque;
       public         postgres    false    7            �            1259    18927    tb_calificacion    TABLE     �   CREATE TABLE tb_calificacion (
    codigo character varying(5) NOT NULL,
    codigo_detalle_sesion character varying(5),
    puntuacion integer,
    servicio_calificacion character varying(5)
);
 #   DROP TABLE public.tb_calificacion;
       public         postgres    false    7            �            1259    18930    tb_cita    TABLE     �  CREATE TABLE tb_cita (
    codigo character varying(5) NOT NULL,
    codigo_detalle_solicitud character varying(5),
    hora timestamp without time zone,
    codigo_cubiculo character varying(5),
    codigo_agenda character varying(5),
    status character varying(10),
    codigo_notificacion character varying(5),
    codigo_motivo_cancelacion character varying(5),
    fecha character varying(10),
    codigo_esteticista character varying(10)
);
    DROP TABLE public.tb_cita;
       public         postgres    false    7            �            1259    18933 	   tb_ciudad    TABLE     �   CREATE TABLE tb_ciudad (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    estado character varying(5),
    status character varying(8)
);
    DROP TABLE public.tb_ciudad;
       public         postgres    false    7            �            1259    18936 
   tb_cliente    TABLE     s  CREATE TABLE tb_cliente (
    cedula character varying(10) NOT NULL,
    nombre character varying(20),
    apellido character varying(20),
    sexo character varying(9),
    estado_civil character varying(10),
    telefono character varying(12),
    direccion character varying(100),
    correo character varying(45),
    ciudad character varying(5),
    tipo_cliente character varying(5),
    codigo_acuerdo character varying(5),
    codigo_referencia character varying(5),
    codigo_organizacion character varying(10),
    status character varying(8),
    codigo_ocupacion character varying(5),
    fecha_nacimiento date
);
    DROP TABLE public.tb_cliente;
       public         postgres    false    7            �            1259    18939    tb_comentario    TABLE     @  CREATE TABLE tb_comentario (
    tipo_comentario character varying(5),
    codigo character varying(5) NOT NULL,
    descripcion character varying(130),
    codigo_usuario character varying(45),
    status character varying(8),
    codigo_usuario_web character varying(10),
    clase_comentario character varying(10)
);
 !   DROP TABLE public.tb_comentario;
       public         postgres    false    7            �            1259    18942    tb_comentario_cubiculo    TABLE     �   CREATE TABLE tb_comentario_cubiculo (
    codigo_cubiculo character varying(5) NOT NULL,
    codigo_comentario character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 *   DROP TABLE public.tb_comentario_cubiculo;
       public         postgres    false    7            �            1259    18945    tb_comentario_esteticista    TABLE     �   CREATE TABLE tb_comentario_esteticista (
    codigo_comentario character varying(5) NOT NULL,
    codigo_esteticista character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 -   DROP TABLE public.tb_comentario_esteticista;
       public         postgres    false    7            �            1259    18948    tb_comentario_organizacion    TABLE     �   CREATE TABLE tb_comentario_organizacion (
    codigo_organizacion character varying(10) NOT NULL,
    codigo_comentario character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 .   DROP TABLE public.tb_comentario_organizacion;
       public         postgres    false    7            �            1259    18951    tb_comentario_servicio    TABLE     �   CREATE TABLE tb_comentario_servicio (
    codigo_comentario character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 *   DROP TABLE public.tb_comentario_servicio;
       public         postgres    false    7            �            1259    18954    tb_cubiculo    TABLE     �   CREATE TABLE tb_cubiculo (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
    DROP TABLE public.tb_cubiculo;
       public         postgres    false    7            �            1259    18957    tb_detalle_paquete    TABLE     �   CREATE TABLE tb_detalle_paquete (
    codigo_servicio character varying(5) NOT NULL,
    codigo_paquete character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 &   DROP TABLE public.tb_detalle_paquete;
       public         postgres    false    7            �            1259    18960    tb_detalle_sesion    TABLE     5  CREATE TABLE tb_detalle_sesion (
    codigo character varying(5) NOT NULL,
    codigo_avance character varying(5),
    status character varying(8),
    servicio_paquete character varying(5),
    ejecucion_servicio boolean,
    codigo_cita character varying(5),
    codigo_notificacion character varying(5)
);
 %   DROP TABLE public.tb_detalle_sesion;
       public         postgres    false    7            �            1259    18963    tb_detalle_solicitud    TABLE     �   CREATE TABLE tb_detalle_solicitud (
    codigo_solicitud character varying(5),
    codigo_paquete character varying(5),
    codigo_servicio character varying(5),
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 (   DROP TABLE public.tb_detalle_solicitud;
       public         postgres    false    7            �            1259    18966    tb_dia_laborable    TABLE     �   CREATE TABLE tb_dia_laborable (
    descripcion character varying(45),
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 $   DROP TABLE public.tb_dia_laborable;
       public         postgres    false    7            �            1259    18969    tb_diagnostico    TABLE     �  CREATE TABLE tb_diagnostico (
    codigo character varying(5) NOT NULL,
    codigo_cita character varying(5),
    status character varying(8),
    observacion character varying(500),
    peso numeric,
    grasa_corporal numeric,
    masa_muscular numeric,
    medida_brazo_izquierdo numeric,
    medida_brazo_derecho numeric,
    medida_abdomen numeric,
    medida_cintura numeric,
    medida_pierna numeric,
    tipo_de_piel numeric,
    tono_piel_porcentaje numeric
);
 "   DROP TABLE public.tb_diagnostico;
       public         postgres    false    7            �            1259    18975 	   tb_equipo    TABLE     �   CREATE TABLE tb_equipo (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
    DROP TABLE public.tb_equipo;
       public         postgres    false    7            �            1259    18978    tb_equipo_servicio    TABLE     �   CREATE TABLE tb_equipo_servicio (
    codigo character varying(5) NOT NULL,
    codigo_maestro character varying(5),
    codigo_servicio character varying(5),
    status character varying(8)
);
 &   DROP TABLE public.tb_equipo_servicio;
       public         postgres    false    7            �            1259    18981 	   tb_estado    TABLE     �   CREATE TABLE tb_estado (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
    DROP TABLE public.tb_estado;
       public         postgres    false    7            �            1259    18984    tb_esteticista    TABLE     �  CREATE TABLE tb_esteticista (
    cedula character varying(10) NOT NULL,
    nombre character varying(20),
    apellido character varying(20),
    sexo character varying(9),
    estado_civil character varying(10),
    telefono character varying(12),
    direccion character varying(100),
    correo character varying(45),
    codigo_estado character varying(5),
    status character varying(8),
    codigo_organizacion character varying(10),
    fecha_nacimiento date
);
 "   DROP TABLE public.tb_esteticista;
       public         postgres    false    7            �            1259    18987    tb_formulario    TABLE     �   CREATE TABLE tb_formulario (
    codigo_respuesta character varying(5) NOT NULL,
    codigo_pregunta character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8),
    tipo_encuesta character varying(5)
);
 !   DROP TABLE public.tb_formulario;
       public         postgres    false    7            �            1259    18990    tb_formulario_cliente    TABLE     �   CREATE TABLE tb_formulario_cliente (
    codigo character varying(5) NOT NULL,
    codigo_cliente character varying(10),
    codigo_formulario character varying(5),
    status character varying(8)
);
 )   DROP TABLE public.tb_formulario_cliente;
       public         postgres    false    7            �            1259    18993    tb_formulario_web_cliente    TABLE     �   CREATE TABLE tb_formulario_web_cliente (
    codigo_respuesta character varying(5),
    codigo_cliente character varying(10),
    codigo character varying(5) NOT NULL
);
 -   DROP TABLE public.tb_formulario_web_cliente;
       public         postgres    false    7            �            1259    18996 	   tb_habito    TABLE     �   CREATE TABLE tb_habito (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
    DROP TABLE public.tb_habito;
       public         postgres    false    7            �            1259    18999    tb_habito_cliente    TABLE     �   CREATE TABLE tb_habito_cliente (
    codigo_habito character varying(5) NOT NULL,
    codigo_cliente character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 %   DROP TABLE public.tb_habito_cliente;
       public         postgres    false    7            �            1259    19002    tb_habito_servicio    TABLE     �   CREATE TABLE tb_habito_servicio (
    codigo_maestro character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 &   DROP TABLE public.tb_habito_servicio;
       public         postgres    false    7            �            1259    19005 
   tb_horario    TABLE     �   CREATE TABLE tb_horario (
    codigo_dia_laborable character varying(5) NOT NULL,
    codigo_bloque character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
    DROP TABLE public.tb_horario;
       public         postgres    false    7            �            1259    19008    tb_horario_equipo    TABLE     �   CREATE TABLE tb_horario_equipo (
    codigo_horario character varying(5) NOT NULL,
    codigo_equipo character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 %   DROP TABLE public.tb_horario_equipo;
       public         postgres    false    7            �            1259    19011    tb_horario_esteticista    TABLE       CREATE TABLE tb_horario_esteticista (
    codigo_horario character varying(5) NOT NULL,
    codigo_esteticista character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8),
    codigo_agenda character varying(5)
);
 *   DROP TABLE public.tb_horario_esteticista;
       public         postgres    false    7            �            1259    19014    tb_incidencia    TABLE     �   CREATE TABLE tb_incidencia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    tipo_incidencia character varying(5),
    status character varying(8)
);
 !   DROP TABLE public.tb_incidencia;
       public         postgres    false    7            �            1259    19017    tb_incidencia_sesion    TABLE     �   CREATE TABLE tb_incidencia_sesion (
    codigo_incidencia character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8),
    codigo_detalle_sesion character varying(5)
);
 (   DROP TABLE public.tb_incidencia_sesion;
       public         postgres    false    7            �            1259    19020    tb_indicador    TABLE     �   CREATE TABLE tb_indicador (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
     DROP TABLE public.tb_indicador;
       public         postgres    false    7            �            1259    19023    tb_indicador_diagnostico    TABLE     �   CREATE TABLE tb_indicador_diagnostico (
    codigo_indicador character varying(5) NOT NULL,
    codigo_diagnostico character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 ,   DROP TABLE public.tb_indicador_diagnostico;
       public         postgres    false    7            �            1259    19026    tb_indicador_servicio    TABLE     �   CREATE TABLE tb_indicador_servicio (
    codigo_maestro character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 )   DROP TABLE public.tb_indicador_servicio;
       public         postgres    false    7            �            1259    19029    tb_motivo_cancelacion    TABLE     �   CREATE TABLE tb_motivo_cancelacion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 )   DROP TABLE public.tb_motivo_cancelacion;
       public         postgres    false    7            �            1259    19032    tb_necesidad    TABLE     �   CREATE TABLE tb_necesidad (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
     DROP TABLE public.tb_necesidad;
       public         postgres    false    7            �            1259    19035    tb_necesidad_cliente    TABLE     �   CREATE TABLE tb_necesidad_cliente (
    codigo_cliente character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL,
    codigo_necesidad character varying(5),
    status character varying(8)
);
 (   DROP TABLE public.tb_necesidad_cliente;
       public         postgres    false    7            �            1259    19038 
   tb_noticia    TABLE     .  CREATE TABLE tb_noticia (
    codigo character varying(5) NOT NULL,
    status character varying(8),
    codigo_sistema character varying(5),
    fecha date,
    tipo_noticia character varying(5),
    titulo character varying(100),
    contenido character varying(500),
    imagen character varying
);
    DROP TABLE public.tb_noticia;
       public         postgres    false    7            �            1259    19044    tb_notificacion    TABLE     �   CREATE TABLE tb_notificacion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8),
    titulo character varying(100),
    fecha date,
    tipo_notificacion character varying(5)
);
 #   DROP TABLE public.tb_notificacion;
       public         postgres    false    7            �            1259    19047    tb_objetivo    TABLE     �   CREATE TABLE tb_objetivo (
    codigo character varying(5) NOT NULL,
    descripcion character varying(500),
    tipo_objetivo character varying(10),
    codigo_organizacion character varying(10),
    status character varying(8)
);
    DROP TABLE public.tb_objetivo;
       public         postgres    false    7            �            1259    19053    tb_ocupacion    TABLE     �   CREATE TABLE tb_ocupacion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
     DROP TABLE public.tb_ocupacion;
       public         postgres    false    7            �            1259    19056    tb_ocupacion_servicio    TABLE     �   CREATE TABLE tb_ocupacion_servicio (
    codigo_maestro character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 )   DROP TABLE public.tb_ocupacion_servicio;
       public         postgres    false    7            �            1259    19059 	   tb_opcion    TABLE       CREATE TABLE tb_opcion (
    codigo character varying(5) NOT NULL,
    codigo_padre character varying(5),
    vinculo character varying(100),
    texto character varying(100),
    status character varying(8),
    icono character varying(50),
    tabla character varying(25)
);
    DROP TABLE public.tb_opcion;
       public         postgres    false    7            �            1259    27091    tb_opcion_rol    TABLE     �   CREATE TABLE tb_opcion_rol (
    codigo character varying(5) NOT NULL,
    codigo_opcion character varying(5),
    status character varying(8),
    codigo_rol character varying(5)
);
 !   DROP TABLE public.tb_opcion_rol;
       public         postgres    false    7            �            1259    19065    tb_organizacion    TABLE     �  CREATE TABLE tb_organizacion (
    rif character varying(10) NOT NULL,
    nombre character varying(50),
    tipo_organizacion character varying(5),
    correo character varying(45),
    direccion character varying(100),
    telefono character varying(12),
    mision character varying(500),
    vision character varying(500),
    status character varying(8),
    hora_entrada timestamp with time zone,
    hora_salida timestamp with time zone
);
 #   DROP TABLE public.tb_organizacion;
       public         postgres    false    7            �            1259    19071 
   tb_paquete    TABLE     �   CREATE TABLE tb_paquete (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    tipo_paquete character varying(12),
    status character varying(8),
    imagen character varying
);
    DROP TABLE public.tb_paquete;
       public         postgres    false    7            �            1259    19077    tb_perfil_usuario    TABLE     �  CREATE TABLE tb_perfil_usuario (
    cedula character varying(10) NOT NULL,
    nombre character varying(20),
    apellido character varying(20),
    sexo character varying(9),
    estado_civil character varying(10),
    telefono character varying(12),
    direccion character varying(100),
    correo character varying(45),
    codigo_estado character varying(5),
    status character varying(8),
    codigo_organizacion character varying(10),
    fecha_nacimiento date
);
 %   DROP TABLE public.tb_perfil_usuario;
       public         postgres    false    7            �            1259    19080    tb_preferencia    TABLE     �   CREATE TABLE tb_preferencia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8),
    tipo_preferencia character varying,
    imagen character varying
);
 "   DROP TABLE public.tb_preferencia;
       public         postgres    false    7            �            1259    19086    tb_preferencia_cliente    TABLE     �   CREATE TABLE tb_preferencia_cliente (
    codigo_preferencia character varying(5) NOT NULL,
    codigo_cliente character varying(10) NOT NULL,
    codigo character varying(5) NOT NULL
);
 *   DROP TABLE public.tb_preferencia_cliente;
       public         postgres    false    7            �            1259    19089    tb_preferencia_servicio    TABLE     �   CREATE TABLE tb_preferencia_servicio (
    codigo_preferencia character varying(5) NOT NULL,
    codigo_servicio character varying(5) NOT NULL,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 +   DROP TABLE public.tb_preferencia_servicio;
       public         postgres    false    7            �            1259    19092    tb_pregunta    TABLE     �   CREATE TABLE tb_pregunta (
    descripcion character varying(45),
    codigo character varying(5) NOT NULL,
    status character varying(8),
    tipo_pregunta character varying(5)
);
    DROP TABLE public.tb_pregunta;
       public         postgres    false    7            �            1259    19095    tb_promocion    TABLE       CREATE TABLE tb_promocion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    codigo_paquete character varying(5),
    fecha_inicio date,
    fecha_fin date,
    status character varying(8),
    eslogan character varying(200)
);
     DROP TABLE public.tb_promocion;
       public         postgres    false    7            �            1259    19098    tb_red_social    TABLE     �   CREATE TABLE tb_red_social (
    codigo character varying(5) NOT NULL,
    url character varying(30),
    tipo_red_social character varying(5),
    codigo_organizacion character varying(10),
    status character varying(8)
);
 !   DROP TABLE public.tb_red_social;
       public         postgres    false    7            �            1259    19101    tb_referencia    TABLE     �   CREATE TABLE tb_referencia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 !   DROP TABLE public.tb_referencia;
       public         postgres    false    7            �            1259    19104    tb_respuesta    TABLE     �   CREATE TABLE tb_respuesta (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
     DROP TABLE public.tb_respuesta;
       public         postgres    false    7            �            1259    19107    tb_respuesta_comentario    TABLE     �   CREATE TABLE tb_respuesta_comentario (
    codigo character varying(5) NOT NULL,
    codigo_comentario character varying(5),
    descripcion character varying(130),
    status character varying(8)
);
 +   DROP TABLE public.tb_respuesta_comentario;
       public         postgres    false    7            �            1259    19110    tb_respuesta_formulario_web    TABLE     �   CREATE TABLE tb_respuesta_formulario_web (
    codigo character varying(5) NOT NULL,
    pregunta character varying(200),
    respuesta character varying(200),
    codigo_cliente character varying(10)
);
 /   DROP TABLE public.tb_respuesta_formulario_web;
       public         postgres    false    7            �            1259    19113    tb_rol    TABLE     �   CREATE TABLE tb_rol (
    descripcion character varying(45),
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
    DROP TABLE public.tb_rol;
       public         postgres    false    7            �            1259    19116    tb_servicio    TABLE     +  CREATE TABLE tb_servicio (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    tipo_servicio character varying(5),
    codigo_organizacion character varying(10),
    imagen character varying,
    status character varying(8),
    duracion timestamp with time zone
);
    DROP TABLE public.tb_servicio;
       public         postgres    false    7            �            1259    19122    tb_sesion_por_paquete    TABLE     �   CREATE TABLE tb_sesion_por_paquete (
    codigo_paquete character varying(5),
    cantidad integer,
    codigo character varying(5) NOT NULL,
    status character varying(8)
);
 )   DROP TABLE public.tb_sesion_por_paquete;
       public         postgres    false    7            �            1259    19125 
   tb_sistema    TABLE     �   CREATE TABLE tb_sistema (
    codigo character varying(5) NOT NULL,
    status character varying(8),
    nombre character varying(45),
    logo bytea
);
    DROP TABLE public.tb_sistema;
       public         postgres    false    7            �            1259    19131 	   tb_slider    TABLE     �   CREATE TABLE tb_slider (
    codigo character varying(5) NOT NULL,
    titulo character varying(45),
    leer_mas character varying(500),
    status character varying(8),
    subtitulo character varying(100),
    codigo_sistema character varying(5)
);
    DROP TABLE public.tb_slider;
       public         postgres    false    7            �            1259    19137    tb_solicitud    TABLE     �   CREATE TABLE tb_solicitud (
    codigo character varying(5) NOT NULL,
    codigo_cliente character varying(10),
    status character varying(8),
    fecha character varying(10)
);
     DROP TABLE public.tb_solicitud;
       public         postgres    false    7            �            1259    19140    tb_tipo_acuerdo    TABLE     �   CREATE TABLE tb_tipo_acuerdo (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 #   DROP TABLE public.tb_tipo_acuerdo;
       public         postgres    false    7            �            1259    19143    tb_tipo_cliente    TABLE     �   CREATE TABLE tb_tipo_cliente (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 #   DROP TABLE public.tb_tipo_cliente;
       public         postgres    false    7            �            1259    19146    tb_tipo_comentario    TABLE     �   CREATE TABLE tb_tipo_comentario (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 &   DROP TABLE public.tb_tipo_comentario;
       public         postgres    false    7            �            1259    19149    tb_tipo_encuesta    TABLE     �   CREATE TABLE tb_tipo_encuesta (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 $   DROP TABLE public.tb_tipo_encuesta;
       public         postgres    false    7            �            1259    19152    tb_tipo_incidencia    TABLE     �   CREATE TABLE tb_tipo_incidencia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 &   DROP TABLE public.tb_tipo_incidencia;
       public         postgres    false    7            �            1259    19155    tb_tipo_noticia    TABLE     �   CREATE TABLE tb_tipo_noticia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 #   DROP TABLE public.tb_tipo_noticia;
       public         postgres    false    7            �            1259    19158    tb_tipo_notificacion    TABLE     �   CREATE TABLE tb_tipo_notificacion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 (   DROP TABLE public.tb_tipo_notificacion;
       public         postgres    false    7            �            1259    19161    tb_tipo_organizacion    TABLE     �   CREATE TABLE tb_tipo_organizacion (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 (   DROP TABLE public.tb_tipo_organizacion;
       public         postgres    false    7            �            1259    19164    tb_tipo_preferencia    TABLE     �   CREATE TABLE tb_tipo_preferencia (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 '   DROP TABLE public.tb_tipo_preferencia;
       public         postgres    false    7            �            1259    19167    tb_tipo_pregunta    TABLE     �   CREATE TABLE tb_tipo_pregunta (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8)
);
 $   DROP TABLE public.tb_tipo_pregunta;
       public         postgres    false    7            �            1259    19170    tb_tipo_red_social    TABLE     �   CREATE TABLE tb_tipo_red_social (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8),
    logo bytea
);
 &   DROP TABLE public.tb_tipo_red_social;
       public         postgres    false    7            �            1259    19176    tb_tipo_servicio    TABLE     �   CREATE TABLE tb_tipo_servicio (
    codigo character varying(5) NOT NULL,
    descripcion character varying(45),
    status character varying(8),
    imagen character varying
);
 $   DROP TABLE public.tb_tipo_servicio;
       public         postgres    false    7            �            1259    19182 
   tb_usuario    TABLE     �   CREATE TABLE tb_usuario (
    usuario character varying(45) NOT NULL,
    contrasenna character varying(10),
    rol character varying(5),
    status character varying(8)
);
    DROP TABLE public.tb_usuario;
       public         postgres    false    7            �            1259    19185    tb_usuario_web    TABLE     �   CREATE TABLE tb_usuario_web (
    cedula character varying(10) NOT NULL,
    nombre character varying(20),
    apellido character varying(20),
    correo character varying(45),
    telefono character varying(12),
    codigo_ciudad character varying(5)
);
 "   DROP TABLE public.tb_usuario_web;
       public         postgres    false    7            
          0    18900 
   tb_acuerdo 
   TABLE DATA                     public       postgres    false    171   ��      
          0    18906 	   tb_agenda 
   TABLE DATA                     public       postgres    false    172   �      
          0    18909    tb_antecedente 
   TABLE DATA                     public       postgres    false    173   ��      
          0    18912    tb_antecedente_cliente 
   TABLE DATA                     public       postgres    false    174   V�      
          0    18915    tb_antecedente_servicio 
   TABLE DATA                     public       postgres    false    175   ��      
          0    18918 	   tb_avance 
   TABLE DATA                     public       postgres    false    176   �      
          0    18921    tb_avance_servicio 
   TABLE DATA                     public       postgres    false    177   *�      	
          0    18924 	   tb_bloque 
   TABLE DATA                     public       postgres    false    178   D�      

          0    18927    tb_calificacion 
   TABLE DATA                     public       postgres    false    179   ��      
          0    18930    tb_cita 
   TABLE DATA                     public       postgres    false    180   ��      
          0    18933 	   tb_ciudad 
   TABLE DATA                     public       postgres    false    181   t�      
          0    18936 
   tb_cliente 
   TABLE DATA                     public       postgres    false    182   G�      
          0    18939    tb_comentario 
   TABLE DATA                     public       postgres    false    183   ��      
          0    18942    tb_comentario_cubiculo 
   TABLE DATA                     public       postgres    false    184   ��      
          0    18945    tb_comentario_esteticista 
   TABLE DATA                     public       postgres    false    185   �      
          0    18948    tb_comentario_organizacion 
   TABLE DATA                     public       postgres    false    186   %�      
          0    18951    tb_comentario_servicio 
   TABLE DATA                     public       postgres    false    187   ?�      
          0    18954    tb_cubiculo 
   TABLE DATA                     public       postgres    false    188   Y�      
          0    18957    tb_detalle_paquete 
   TABLE DATA                     public       postgres    false    189   ��      
          0    18960    tb_detalle_sesion 
   TABLE DATA                     public       postgres    false    190   ��      
          0    18963    tb_detalle_solicitud 
   TABLE DATA                     public       postgres    false    191   �      
          0    18966    tb_dia_laborable 
   TABLE DATA                     public       postgres    false    192   ~�      
          0    18969    tb_diagnostico 
   TABLE DATA                     public       postgres    false    193   �      
          0    18975 	   tb_equipo 
   TABLE DATA                     public       postgres    false    194   �      
          0    18978    tb_equipo_servicio 
   TABLE DATA                     public       postgres    false    195   ��      
          0    18981 	   tb_estado 
   TABLE DATA                     public       postgres    false    196   L�      
          0    18984    tb_esteticista 
   TABLE DATA                     public       postgres    false    197   ��      
          0    18987    tb_formulario 
   TABLE DATA                     public       postgres    false    198   4�      
          0    18990    tb_formulario_cliente 
   TABLE DATA                     public       postgres    false    199   N�      
          0    18993    tb_formulario_web_cliente 
   TABLE DATA                     public       postgres    false    200   h�       
          0    18996 	   tb_habito 
   TABLE DATA                     public       postgres    false    201   ��      !
          0    18999    tb_habito_cliente 
   TABLE DATA                     public       postgres    false    202   B�      "
          0    19002    tb_habito_servicio 
   TABLE DATA                     public       postgres    false    203   ��      #
          0    19005 
   tb_horario 
   TABLE DATA                     public       postgres    false    204   S�      $
          0    19008    tb_horario_equipo 
   TABLE DATA                     public       postgres    false    205   �      %
          0    19011    tb_horario_esteticista 
   TABLE DATA                     public       postgres    false    206   �      &
          0    19014    tb_incidencia 
   TABLE DATA                     public       postgres    false    207   .�      '
          0    19017    tb_incidencia_sesion 
   TABLE DATA                     public       postgres    false    208   H�      (
          0    19020    tb_indicador 
   TABLE DATA                     public       postgres    false    209   b�      )
          0    19023    tb_indicador_diagnostico 
   TABLE DATA                     public       postgres    false    210   ��      *
          0    19026    tb_indicador_servicio 
   TABLE DATA                     public       postgres    false    211   ��      +
          0    19029    tb_motivo_cancelacion 
   TABLE DATA                     public       postgres    false    212   ��      ,
          0    19032    tb_necesidad 
   TABLE DATA                     public       postgres    false    213   �      -
          0    19035    tb_necesidad_cliente 
   TABLE DATA                     public       postgres    false    214   0�      .
          0    19038 
   tb_noticia 
   TABLE DATA                     public       postgres    false    215   J�      /
          0    19044    tb_notificacion 
   TABLE DATA                     public       postgres    false    216   d�      0
          0    19047    tb_objetivo 
   TABLE DATA                     public       postgres    false    217   k�      1
          0    19053    tb_ocupacion 
   TABLE DATA                     public       postgres    false    218   ��      2
          0    19056    tb_ocupacion_servicio 
   TABLE DATA                     public       postgres    false    219   
�      3
          0    19059 	   tb_opcion 
   TABLE DATA                     public       postgres    false    220   $�      U
          0    27091    tb_opcion_rol 
   TABLE DATA                     public       postgres    false    254   ��      4
          0    19065    tb_organizacion 
   TABLE DATA                     public       postgres    false    221   ��      5
          0    19071 
   tb_paquete 
   TABLE DATA                     public       postgres    false    222   |�      6
          0    19077    tb_perfil_usuario 
   TABLE DATA                     public       postgres    false    223    �      7
          0    19080    tb_preferencia 
   TABLE DATA                     public       postgres    false    224   ��      8
          0    19086    tb_preferencia_cliente 
   TABLE DATA                     public       postgres    false    225   �      9
          0    19089    tb_preferencia_servicio 
   TABLE DATA                     public       postgres    false    226   ��      :
          0    19092    tb_pregunta 
   TABLE DATA                     public       postgres    false    227   C�      ;
          0    19095    tb_promocion 
   TABLE DATA                     public       postgres    false    228   ]�      <
          0    19098    tb_red_social 
   TABLE DATA                     public       postgres    false    229   w�      =
          0    19101    tb_referencia 
   TABLE DATA                     public       postgres    false    230   ��      >
          0    19104    tb_respuesta 
   TABLE DATA                     public       postgres    false    231   5�      ?
          0    19107    tb_respuesta_comentario 
   TABLE DATA                     public       postgres    false    232   ��      @
          0    19110    tb_respuesta_formulario_web 
   TABLE DATA                     public       postgres    false    233   !�      A
          0    19113    tb_rol 
   TABLE DATA                     public       postgres    false    234   ;�      B
          0    19116    tb_servicio 
   TABLE DATA                     public       postgres    false    235   ��      C
          0    19122    tb_sesion_por_paquete 
   TABLE DATA                     public       postgres    false    236   ��      D
          0    19125 
   tb_sistema 
   TABLE DATA                     public       postgres    false    237   ��      E
          0    19131 	   tb_slider 
   TABLE DATA                     public       postgres    false    238   T�      F
          0    19137    tb_solicitud 
   TABLE DATA                     public       postgres    false    239   ��      G
          0    19140    tb_tipo_acuerdo 
   TABLE DATA                     public       postgres    false    240   0�      H
          0    19143    tb_tipo_cliente 
   TABLE DATA                     public       postgres    false    241   ��      I
          0    19146    tb_tipo_comentario 
   TABLE DATA                     public       postgres    false    242   �      J
          0    19149    tb_tipo_encuesta 
   TABLE DATA                     public       postgres    false    243   ��      K
          0    19152    tb_tipo_incidencia 
   TABLE DATA                     public       postgres    false    244   D�      L
          0    19155    tb_tipo_noticia 
   TABLE DATA                     public       postgres    false    245   ��      M
          0    19158    tb_tipo_notificacion 
   TABLE DATA                     public       postgres    false    246   Q�      N
          0    19161    tb_tipo_organizacion 
   TABLE DATA                     public       postgres    false    247   ��      O
          0    19164    tb_tipo_preferencia 
   TABLE DATA                     public       postgres    false    248   l�      P
          0    19167    tb_tipo_pregunta 
   TABLE DATA                     public       postgres    false    249   ��      Q
          0    19170    tb_tipo_red_social 
   TABLE DATA                     public       postgres    false    250   w�      R
          0    19176    tb_tipo_servicio 
   TABLE DATA                     public       postgres    false    251   ��      S
          0    19182 
   tb_usuario 
   TABLE DATA                     public       postgres    false    252   ~�      T
          0    19185    tb_usuario_web 
   TABLE DATA                     public       postgres    false    253   �      w           2606    19189 
   id_acuerdo 
   CONSTRAINT     P   ALTER TABLE ONLY tb_acuerdo
    ADD CONSTRAINT id_acuerdo PRIMARY KEY (codigo);
 ?   ALTER TABLE ONLY public.tb_acuerdo DROP CONSTRAINT id_acuerdo;
       public         postgres    false    171    171            y           2606    19191 	   id_agenda 
   CONSTRAINT     N   ALTER TABLE ONLY tb_agenda
    ADD CONSTRAINT id_agenda PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_agenda DROP CONSTRAINT id_agenda;
       public         postgres    false    172    172            {           2606    19193    id_antecedente 
   CONSTRAINT     X   ALTER TABLE ONLY tb_antecedente
    ADD CONSTRAINT id_antecedente PRIMARY KEY (codigo);
 G   ALTER TABLE ONLY public.tb_antecedente DROP CONSTRAINT id_antecedente;
       public         postgres    false    173    173                       2606    19195    id_antecedente_cliente 
   CONSTRAINT     h   ALTER TABLE ONLY tb_antecedente_cliente
    ADD CONSTRAINT id_antecedente_cliente PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_antecedente_cliente DROP CONSTRAINT id_antecedente_cliente;
       public         postgres    false    174    174            �           2606    19197    id_antecende_servicio 
   CONSTRAINT     h   ALTER TABLE ONLY tb_antecedente_servicio
    ADD CONSTRAINT id_antecende_servicio PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_antecedente_servicio DROP CONSTRAINT id_antecende_servicio;
       public         postgres    false    175    175            �           2606    19199 	   id_avance 
   CONSTRAINT     N   ALTER TABLE ONLY tb_avance
    ADD CONSTRAINT id_avance PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_avance DROP CONSTRAINT id_avance;
       public         postgres    false    176    176            �           2606    19201    id_avance_servicio 
   CONSTRAINT     `   ALTER TABLE ONLY tb_avance_servicio
    ADD CONSTRAINT id_avance_servicio PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_avance_servicio DROP CONSTRAINT id_avance_servicio;
       public         postgres    false    177    177            �           2606    19203 	   id_bloque 
   CONSTRAINT     N   ALTER TABLE ONLY tb_bloque
    ADD CONSTRAINT id_bloque PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_bloque DROP CONSTRAINT id_bloque;
       public         postgres    false    178    178            �           2606    19205    id_calificacion 
   CONSTRAINT     Z   ALTER TABLE ONLY tb_calificacion
    ADD CONSTRAINT id_calificacion PRIMARY KEY (codigo);
 I   ALTER TABLE ONLY public.tb_calificacion DROP CONSTRAINT id_calificacion;
       public         postgres    false    179    179            �           2606    19207    id_cita 
   CONSTRAINT     J   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT id_cita PRIMARY KEY (codigo);
 9   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT id_cita;
       public         postgres    false    180    180            �           2606    19209 	   id_ciudad 
   CONSTRAINT     N   ALTER TABLE ONLY tb_ciudad
    ADD CONSTRAINT id_ciudad PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_ciudad DROP CONSTRAINT id_ciudad;
       public         postgres    false    181    181            �           2606    19211 
   id_cliente 
   CONSTRAINT     P   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT id_cliente PRIMARY KEY (cedula);
 ?   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT id_cliente;
       public         postgres    false    182    182            �           2606    19213    id_codigo_habito_cliente 
   CONSTRAINT     e   ALTER TABLE ONLY tb_habito_cliente
    ADD CONSTRAINT id_codigo_habito_cliente PRIMARY KEY (codigo);
 T   ALTER TABLE ONLY public.tb_habito_cliente DROP CONSTRAINT id_codigo_habito_cliente;
       public         postgres    false    202    202            �           2606    19215    id_codigo_necesidad_cliente 
   CONSTRAINT     k   ALTER TABLE ONLY tb_necesidad_cliente
    ADD CONSTRAINT id_codigo_necesidad_cliente PRIMARY KEY (codigo);
 Z   ALTER TABLE ONLY public.tb_necesidad_cliente DROP CONSTRAINT id_codigo_necesidad_cliente;
       public         postgres    false    214    214            !	           2606    19217    id_codigo_tipo_preferencia 
   CONSTRAINT     i   ALTER TABLE ONLY tb_tipo_preferencia
    ADD CONSTRAINT id_codigo_tipo_preferencia PRIMARY KEY (codigo);
 X   ALTER TABLE ONLY public.tb_tipo_preferencia DROP CONSTRAINT id_codigo_tipo_preferencia;
       public         postgres    false    248    248            �           2606    19219    id_comentario 
   CONSTRAINT     V   ALTER TABLE ONLY tb_comentario
    ADD CONSTRAINT id_comentario PRIMARY KEY (codigo);
 E   ALTER TABLE ONLY public.tb_comentario DROP CONSTRAINT id_comentario;
       public         postgres    false    183    183            �           2606    19221    id_comentario_cubiculo 
   CONSTRAINT     h   ALTER TABLE ONLY tb_comentario_cubiculo
    ADD CONSTRAINT id_comentario_cubiculo PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_comentario_cubiculo DROP CONSTRAINT id_comentario_cubiculo;
       public         postgres    false    184    184            �           2606    19223    id_comentario_esteticista 
   CONSTRAINT     n   ALTER TABLE ONLY tb_comentario_esteticista
    ADD CONSTRAINT id_comentario_esteticista PRIMARY KEY (codigo);
 ]   ALTER TABLE ONLY public.tb_comentario_esteticista DROP CONSTRAINT id_comentario_esteticista;
       public         postgres    false    185    185            �           2606    19225    id_comentario_oganizacion 
   CONSTRAINT     o   ALTER TABLE ONLY tb_comentario_organizacion
    ADD CONSTRAINT id_comentario_oganizacion PRIMARY KEY (codigo);
 ^   ALTER TABLE ONLY public.tb_comentario_organizacion DROP CONSTRAINT id_comentario_oganizacion;
       public         postgres    false    186    186            �           2606    19227    id_comentario_servicio 
   CONSTRAINT     h   ALTER TABLE ONLY tb_comentario_servicio
    ADD CONSTRAINT id_comentario_servicio PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_comentario_servicio DROP CONSTRAINT id_comentario_servicio;
       public         postgres    false    187    187            �           2606    19229    id_cubiculo 
   CONSTRAINT     R   ALTER TABLE ONLY tb_cubiculo
    ADD CONSTRAINT id_cubiculo PRIMARY KEY (codigo);
 A   ALTER TABLE ONLY public.tb_cubiculo DROP CONSTRAINT id_cubiculo;
       public         postgres    false    188    188            �           2606    19231    id_detalle_paquete 
   CONSTRAINT     `   ALTER TABLE ONLY tb_detalle_paquete
    ADD CONSTRAINT id_detalle_paquete PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_detalle_paquete DROP CONSTRAINT id_detalle_paquete;
       public         postgres    false    189    189            �           2606    19233    id_detalle_sesion 
   CONSTRAINT     ^   ALTER TABLE ONLY tb_detalle_sesion
    ADD CONSTRAINT id_detalle_sesion PRIMARY KEY (codigo);
 M   ALTER TABLE ONLY public.tb_detalle_sesion DROP CONSTRAINT id_detalle_sesion;
       public         postgres    false    190    190            �           2606    19235    id_detalle_solicitud 
   CONSTRAINT     d   ALTER TABLE ONLY tb_detalle_solicitud
    ADD CONSTRAINT id_detalle_solicitud PRIMARY KEY (codigo);
 S   ALTER TABLE ONLY public.tb_detalle_solicitud DROP CONSTRAINT id_detalle_solicitud;
       public         postgres    false    191    191            �           2606    19237    id_dia_laborable 
   CONSTRAINT     \   ALTER TABLE ONLY tb_dia_laborable
    ADD CONSTRAINT id_dia_laborable PRIMARY KEY (codigo);
 K   ALTER TABLE ONLY public.tb_dia_laborable DROP CONSTRAINT id_dia_laborable;
       public         postgres    false    192    192            �           2606    19239    id_diagnostico 
   CONSTRAINT     X   ALTER TABLE ONLY tb_diagnostico
    ADD CONSTRAINT id_diagnostico PRIMARY KEY (codigo);
 G   ALTER TABLE ONLY public.tb_diagnostico DROP CONSTRAINT id_diagnostico;
       public         postgres    false    193    193            �           2606    19241 	   id_equipo 
   CONSTRAINT     N   ALTER TABLE ONLY tb_equipo
    ADD CONSTRAINT id_equipo PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_equipo DROP CONSTRAINT id_equipo;
       public         postgres    false    194    194            �           2606    19243    id_equipo_servicio 
   CONSTRAINT     `   ALTER TABLE ONLY tb_equipo_servicio
    ADD CONSTRAINT id_equipo_servicio PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_equipo_servicio DROP CONSTRAINT id_equipo_servicio;
       public         postgres    false    195    195            �           2606    19245 	   id_estado 
   CONSTRAINT     N   ALTER TABLE ONLY tb_estado
    ADD CONSTRAINT id_estado PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_estado DROP CONSTRAINT id_estado;
       public         postgres    false    196    196            �           2606    19247    id_esteticista 
   CONSTRAINT     X   ALTER TABLE ONLY tb_esteticista
    ADD CONSTRAINT id_esteticista PRIMARY KEY (cedula);
 G   ALTER TABLE ONLY public.tb_esteticista DROP CONSTRAINT id_esteticista;
       public         postgres    false    197    197            �           2606    19249    id_formulario 
   CONSTRAINT     V   ALTER TABLE ONLY tb_formulario
    ADD CONSTRAINT id_formulario PRIMARY KEY (codigo);
 E   ALTER TABLE ONLY public.tb_formulario DROP CONSTRAINT id_formulario;
       public         postgres    false    198    198            �           2606    19251    id_formulario_cliente 
   CONSTRAINT     f   ALTER TABLE ONLY tb_formulario_cliente
    ADD CONSTRAINT id_formulario_cliente PRIMARY KEY (codigo);
 U   ALTER TABLE ONLY public.tb_formulario_cliente DROP CONSTRAINT id_formulario_cliente;
       public         postgres    false    199    199            �           2606    19253    id_formulario_web_cliente 
   CONSTRAINT     n   ALTER TABLE ONLY tb_formulario_web_cliente
    ADD CONSTRAINT id_formulario_web_cliente PRIMARY KEY (codigo);
 ]   ALTER TABLE ONLY public.tb_formulario_web_cliente DROP CONSTRAINT id_formulario_web_cliente;
       public         postgres    false    200    200            �           2606    19255 	   id_habito 
   CONSTRAINT     N   ALTER TABLE ONLY tb_habito
    ADD CONSTRAINT id_habito PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_habito DROP CONSTRAINT id_habito;
       public         postgres    false    201    201            �           2606    19257    id_habito_servicio 
   CONSTRAINT     `   ALTER TABLE ONLY tb_habito_servicio
    ADD CONSTRAINT id_habito_servicio PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_habito_servicio DROP CONSTRAINT id_habito_servicio;
       public         postgres    false    203    203            �           2606    19259 
   id_horario 
   CONSTRAINT     P   ALTER TABLE ONLY tb_horario
    ADD CONSTRAINT id_horario PRIMARY KEY (codigo);
 ?   ALTER TABLE ONLY public.tb_horario DROP CONSTRAINT id_horario;
       public         postgres    false    204    204            �           2606    19261    id_horario_equipo 
   CONSTRAINT     ^   ALTER TABLE ONLY tb_horario_equipo
    ADD CONSTRAINT id_horario_equipo PRIMARY KEY (codigo);
 M   ALTER TABLE ONLY public.tb_horario_equipo DROP CONSTRAINT id_horario_equipo;
       public         postgres    false    205    205            �           2606    19263    id_horario_esteticista 
   CONSTRAINT     h   ALTER TABLE ONLY tb_horario_esteticista
    ADD CONSTRAINT id_horario_esteticista PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_horario_esteticista DROP CONSTRAINT id_horario_esteticista;
       public         postgres    false    206    206            �           2606    19265    id_incidencia 
   CONSTRAINT     V   ALTER TABLE ONLY tb_incidencia
    ADD CONSTRAINT id_incidencia PRIMARY KEY (codigo);
 E   ALTER TABLE ONLY public.tb_incidencia DROP CONSTRAINT id_incidencia;
       public         postgres    false    207    207            �           2606    19267    id_incidencia_sesion 
   CONSTRAINT     d   ALTER TABLE ONLY tb_incidencia_sesion
    ADD CONSTRAINT id_incidencia_sesion PRIMARY KEY (codigo);
 S   ALTER TABLE ONLY public.tb_incidencia_sesion DROP CONSTRAINT id_incidencia_sesion;
       public         postgres    false    208    208            �           2606    19269    id_indicador 
   CONSTRAINT     T   ALTER TABLE ONLY tb_indicador
    ADD CONSTRAINT id_indicador PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_indicador DROP CONSTRAINT id_indicador;
       public         postgres    false    209    209            �           2606    19271    id_indicador_diagnostico 
   CONSTRAINT     l   ALTER TABLE ONLY tb_indicador_diagnostico
    ADD CONSTRAINT id_indicador_diagnostico PRIMARY KEY (codigo);
 [   ALTER TABLE ONLY public.tb_indicador_diagnostico DROP CONSTRAINT id_indicador_diagnostico;
       public         postgres    false    210    210            �           2606    19273    id_indicador_servicio 
   CONSTRAINT     f   ALTER TABLE ONLY tb_indicador_servicio
    ADD CONSTRAINT id_indicador_servicio PRIMARY KEY (codigo);
 U   ALTER TABLE ONLY public.tb_indicador_servicio DROP CONSTRAINT id_indicador_servicio;
       public         postgres    false    211    211            �           2606    19275    id_motivo_cancelacion 
   CONSTRAINT     f   ALTER TABLE ONLY tb_motivo_cancelacion
    ADD CONSTRAINT id_motivo_cancelacion PRIMARY KEY (codigo);
 U   ALTER TABLE ONLY public.tb_motivo_cancelacion DROP CONSTRAINT id_motivo_cancelacion;
       public         postgres    false    212    212            �           2606    19277    id_necesidad 
   CONSTRAINT     T   ALTER TABLE ONLY tb_necesidad
    ADD CONSTRAINT id_necesidad PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_necesidad DROP CONSTRAINT id_necesidad;
       public         postgres    false    213    213            �           2606    19279 
   id_noticia 
   CONSTRAINT     P   ALTER TABLE ONLY tb_noticia
    ADD CONSTRAINT id_noticia PRIMARY KEY (codigo);
 ?   ALTER TABLE ONLY public.tb_noticia DROP CONSTRAINT id_noticia;
       public         postgres    false    215    215            �           2606    19281    id_notificacion 
   CONSTRAINT     Z   ALTER TABLE ONLY tb_notificacion
    ADD CONSTRAINT id_notificacion PRIMARY KEY (codigo);
 I   ALTER TABLE ONLY public.tb_notificacion DROP CONSTRAINT id_notificacion;
       public         postgres    false    216    216            �           2606    19283    id_objetivo 
   CONSTRAINT     R   ALTER TABLE ONLY tb_objetivo
    ADD CONSTRAINT id_objetivo PRIMARY KEY (codigo);
 A   ALTER TABLE ONLY public.tb_objetivo DROP CONSTRAINT id_objetivo;
       public         postgres    false    217    217            �           2606    19285    id_ocupacion 
   CONSTRAINT     T   ALTER TABLE ONLY tb_ocupacion
    ADD CONSTRAINT id_ocupacion PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_ocupacion DROP CONSTRAINT id_ocupacion;
       public         postgres    false    218    218            �           2606    19287    id_ocupacion_servicio 
   CONSTRAINT     f   ALTER TABLE ONLY tb_ocupacion_servicio
    ADD CONSTRAINT id_ocupacion_servicio PRIMARY KEY (codigo);
 U   ALTER TABLE ONLY public.tb_ocupacion_servicio DROP CONSTRAINT id_ocupacion_servicio;
       public         postgres    false    219    219            �           2606    19289 	   id_opcion 
   CONSTRAINT     N   ALTER TABLE ONLY tb_opcion
    ADD CONSTRAINT id_opcion PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_opcion DROP CONSTRAINT id_opcion;
       public         postgres    false    220    220            �           2606    19293    id_organizacion 
   CONSTRAINT     W   ALTER TABLE ONLY tb_organizacion
    ADD CONSTRAINT id_organizacion PRIMARY KEY (rif);
 I   ALTER TABLE ONLY public.tb_organizacion DROP CONSTRAINT id_organizacion;
       public         postgres    false    221    221            �           2606    19295 
   id_paquete 
   CONSTRAINT     P   ALTER TABLE ONLY tb_paquete
    ADD CONSTRAINT id_paquete PRIMARY KEY (codigo);
 ?   ALTER TABLE ONLY public.tb_paquete DROP CONSTRAINT id_paquete;
       public         postgres    false    222    222            �           2606    19297    id_perfil_usuario 
   CONSTRAINT     ^   ALTER TABLE ONLY tb_perfil_usuario
    ADD CONSTRAINT id_perfil_usuario PRIMARY KEY (cedula);
 M   ALTER TABLE ONLY public.tb_perfil_usuario DROP CONSTRAINT id_perfil_usuario;
       public         postgres    false    223    223            �           2606    19299    id_preferencia 
   CONSTRAINT     X   ALTER TABLE ONLY tb_preferencia
    ADD CONSTRAINT id_preferencia PRIMARY KEY (codigo);
 G   ALTER TABLE ONLY public.tb_preferencia DROP CONSTRAINT id_preferencia;
       public         postgres    false    224    224            �           2606    19301    id_preferencia_cliente 
   CONSTRAINT     h   ALTER TABLE ONLY tb_preferencia_cliente
    ADD CONSTRAINT id_preferencia_cliente PRIMARY KEY (codigo);
 W   ALTER TABLE ONLY public.tb_preferencia_cliente DROP CONSTRAINT id_preferencia_cliente;
       public         postgres    false    225    225            �           2606    19303    id_preferencia_servicio 
   CONSTRAINT     j   ALTER TABLE ONLY tb_preferencia_servicio
    ADD CONSTRAINT id_preferencia_servicio PRIMARY KEY (codigo);
 Y   ALTER TABLE ONLY public.tb_preferencia_servicio DROP CONSTRAINT id_preferencia_servicio;
       public         postgres    false    226    226            �           2606    19305    id_pregunta 
   CONSTRAINT     R   ALTER TABLE ONLY tb_pregunta
    ADD CONSTRAINT id_pregunta PRIMARY KEY (codigo);
 A   ALTER TABLE ONLY public.tb_pregunta DROP CONSTRAINT id_pregunta;
       public         postgres    false    227    227            �           2606    19307    id_promocion 
   CONSTRAINT     T   ALTER TABLE ONLY tb_promocion
    ADD CONSTRAINT id_promocion PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_promocion DROP CONSTRAINT id_promocion;
       public         postgres    false    228    228            �           2606    19309    id_red_social 
   CONSTRAINT     V   ALTER TABLE ONLY tb_red_social
    ADD CONSTRAINT id_red_social PRIMARY KEY (codigo);
 E   ALTER TABLE ONLY public.tb_red_social DROP CONSTRAINT id_red_social;
       public         postgres    false    229    229            �           2606    19311    id_respuesta 
   CONSTRAINT     T   ALTER TABLE ONLY tb_respuesta
    ADD CONSTRAINT id_respuesta PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_respuesta DROP CONSTRAINT id_respuesta;
       public         postgres    false    231    231            �           2606    19313    id_respuesta_comentario 
   CONSTRAINT     j   ALTER TABLE ONLY tb_respuesta_comentario
    ADD CONSTRAINT id_respuesta_comentario PRIMARY KEY (codigo);
 Y   ALTER TABLE ONLY public.tb_respuesta_comentario DROP CONSTRAINT id_respuesta_comentario;
       public         postgres    false    232    232            �           2606    19315    id_respuesta_formulario_web 
   CONSTRAINT     r   ALTER TABLE ONLY tb_respuesta_formulario_web
    ADD CONSTRAINT id_respuesta_formulario_web PRIMARY KEY (codigo);
 a   ALTER TABLE ONLY public.tb_respuesta_formulario_web DROP CONSTRAINT id_respuesta_formulario_web;
       public         postgres    false    233    233            	           2606    19317    id_rol 
   CONSTRAINT     H   ALTER TABLE ONLY tb_rol
    ADD CONSTRAINT id_rol PRIMARY KEY (codigo);
 7   ALTER TABLE ONLY public.tb_rol DROP CONSTRAINT id_rol;
       public         postgres    false    234    234            	           2606    19319    id_servicio 
   CONSTRAINT     R   ALTER TABLE ONLY tb_servicio
    ADD CONSTRAINT id_servicio PRIMARY KEY (codigo);
 A   ALTER TABLE ONLY public.tb_servicio DROP CONSTRAINT id_servicio;
       public         postgres    false    235    235            	           2606    19321    id_sesion_por_paquete 
   CONSTRAINT     f   ALTER TABLE ONLY tb_sesion_por_paquete
    ADD CONSTRAINT id_sesion_por_paquete PRIMARY KEY (codigo);
 U   ALTER TABLE ONLY public.tb_sesion_por_paquete DROP CONSTRAINT id_sesion_por_paquete;
       public         postgres    false    236    236            		           2606    19323 
   id_sistema 
   CONSTRAINT     P   ALTER TABLE ONLY tb_sistema
    ADD CONSTRAINT id_sistema PRIMARY KEY (codigo);
 ?   ALTER TABLE ONLY public.tb_sistema DROP CONSTRAINT id_sistema;
       public         postgres    false    237    237            	           2606    19325 	   id_slider 
   CONSTRAINT     N   ALTER TABLE ONLY tb_slider
    ADD CONSTRAINT id_slider PRIMARY KEY (codigo);
 =   ALTER TABLE ONLY public.tb_slider DROP CONSTRAINT id_slider;
       public         postgres    false    238    238            	           2606    19327    id_solicitud 
   CONSTRAINT     T   ALTER TABLE ONLY tb_solicitud
    ADD CONSTRAINT id_solicitud PRIMARY KEY (codigo);
 C   ALTER TABLE ONLY public.tb_solicitud DROP CONSTRAINT id_solicitud;
       public         postgres    false    239    239            	           2606    19329    id_tipo_acuerdo 
   CONSTRAINT     Z   ALTER TABLE ONLY tb_tipo_acuerdo
    ADD CONSTRAINT id_tipo_acuerdo PRIMARY KEY (codigo);
 I   ALTER TABLE ONLY public.tb_tipo_acuerdo DROP CONSTRAINT id_tipo_acuerdo;
       public         postgres    false    240    240            	           2606    19331    id_tipo_cliente 
   CONSTRAINT     Z   ALTER TABLE ONLY tb_tipo_cliente
    ADD CONSTRAINT id_tipo_cliente PRIMARY KEY (codigo);
 I   ALTER TABLE ONLY public.tb_tipo_cliente DROP CONSTRAINT id_tipo_cliente;
       public         postgres    false    241    241            	           2606    19333    id_tipo_comentario 
   CONSTRAINT     `   ALTER TABLE ONLY tb_tipo_comentario
    ADD CONSTRAINT id_tipo_comentario PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_tipo_comentario DROP CONSTRAINT id_tipo_comentario;
       public         postgres    false    242    242            	           2606    19335    id_tipo_encuesta 
   CONSTRAINT     \   ALTER TABLE ONLY tb_tipo_encuesta
    ADD CONSTRAINT id_tipo_encuesta PRIMARY KEY (codigo);
 K   ALTER TABLE ONLY public.tb_tipo_encuesta DROP CONSTRAINT id_tipo_encuesta;
       public         postgres    false    243    243            	           2606    19337    id_tipo_incidencia 
   CONSTRAINT     `   ALTER TABLE ONLY tb_tipo_incidencia
    ADD CONSTRAINT id_tipo_incidencia PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_tipo_incidencia DROP CONSTRAINT id_tipo_incidencia;
       public         postgres    false    244    244            	           2606    19339    id_tipo_noticia 
   CONSTRAINT     Z   ALTER TABLE ONLY tb_tipo_noticia
    ADD CONSTRAINT id_tipo_noticia PRIMARY KEY (codigo);
 I   ALTER TABLE ONLY public.tb_tipo_noticia DROP CONSTRAINT id_tipo_noticia;
       public         postgres    false    245    245            	           2606    19341    id_tipo_notificacion 
   CONSTRAINT     d   ALTER TABLE ONLY tb_tipo_notificacion
    ADD CONSTRAINT id_tipo_notificacion PRIMARY KEY (codigo);
 S   ALTER TABLE ONLY public.tb_tipo_notificacion DROP CONSTRAINT id_tipo_notificacion;
       public         postgres    false    246    246            	           2606    19343    id_tipo_organizacion 
   CONSTRAINT     d   ALTER TABLE ONLY tb_tipo_organizacion
    ADD CONSTRAINT id_tipo_organizacion PRIMARY KEY (codigo);
 S   ALTER TABLE ONLY public.tb_tipo_organizacion DROP CONSTRAINT id_tipo_organizacion;
       public         postgres    false    247    247            #	           2606    19345    id_tipo_pregunta 
   CONSTRAINT     \   ALTER TABLE ONLY tb_tipo_pregunta
    ADD CONSTRAINT id_tipo_pregunta PRIMARY KEY (codigo);
 K   ALTER TABLE ONLY public.tb_tipo_pregunta DROP CONSTRAINT id_tipo_pregunta;
       public         postgres    false    249    249            %	           2606    19347    id_tipo_red_social 
   CONSTRAINT     `   ALTER TABLE ONLY tb_tipo_red_social
    ADD CONSTRAINT id_tipo_red_social PRIMARY KEY (codigo);
 O   ALTER TABLE ONLY public.tb_tipo_red_social DROP CONSTRAINT id_tipo_red_social;
       public         postgres    false    250    250            �           2606    19349    id_tipo_referencia 
   CONSTRAINT     [   ALTER TABLE ONLY tb_referencia
    ADD CONSTRAINT id_tipo_referencia PRIMARY KEY (codigo);
 J   ALTER TABLE ONLY public.tb_referencia DROP CONSTRAINT id_tipo_referencia;
       public         postgres    false    230    230            '	           2606    19351    id_tipo_servicio 
   CONSTRAINT     \   ALTER TABLE ONLY tb_tipo_servicio
    ADD CONSTRAINT id_tipo_servicio PRIMARY KEY (codigo);
 K   ALTER TABLE ONLY public.tb_tipo_servicio DROP CONSTRAINT id_tipo_servicio;
       public         postgres    false    251    251            *	           2606    19353 
   id_usuario 
   CONSTRAINT     Q   ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT id_usuario PRIMARY KEY (usuario);
 ?   ALTER TABLE ONLY public.tb_usuario DROP CONSTRAINT id_usuario;
       public         postgres    false    252    252            ,	           2606    19355    id_usuario_web 
   CONSTRAINT     X   ALTER TABLE ONLY tb_usuario_web
    ADD CONSTRAINT id_usuario_web PRIMARY KEY (cedula);
 G   ALTER TABLE ONLY public.tb_usuario_web DROP CONSTRAINT id_usuario_web;
       public         postgres    false    253    253            .	           2606    27095    tb_opcion_rol_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY tb_opcion_rol
    ADD CONSTRAINT tb_opcion_rol_pkey PRIMARY KEY (codigo);
 J   ALTER TABLE ONLY public.tb_opcion_rol DROP CONSTRAINT tb_opcion_rol_pkey;
       public         postgres    false    254    254            �           1259    19356 
   fki_agenda    INDEX     @   CREATE INDEX fki_agenda ON tb_cita USING btree (codigo_agenda);
    DROP INDEX public.fki_agenda;
       public         postgres    false    180            |           1259    19357    fki_antecedente_cliente    INDEX     a   CREATE INDEX fki_antecedente_cliente ON tb_antecedente_cliente USING btree (codigo_antecedente);
 +   DROP INDEX public.fki_antecedente_cliente;
       public         postgres    false    174            �           1259    19358    fki_antecedente_servicio    INDEX     _   CREATE INDEX fki_antecedente_servicio ON tb_antecedente_servicio USING btree (codigo_maestro);
 ,   DROP INDEX public.fki_antecedente_servicio;
       public         postgres    false    175            }           1259    19359    fki_cliente_antecedente    INDEX     ]   CREATE INDEX fki_cliente_antecedente ON tb_antecedente_cliente USING btree (codigo_cliente);
 +   DROP INDEX public.fki_cliente_antecedente;
       public         postgres    false    174            	           1259    19360    fki_cliente_solicitud    INDEX     Q   CREATE INDEX fki_cliente_solicitud ON tb_solicitud USING btree (codigo_cliente);
 )   DROP INDEX public.fki_cliente_solicitud;
       public         postgres    false    239            �           1259    19361    fki_cubiculo    INDEX     D   CREATE INDEX fki_cubiculo ON tb_cita USING btree (codigo_cubiculo);
     DROP INDEX public.fki_cubiculo;
       public         postgres    false    180            �           1259    19362 
   fki_estado    INDEX     ;   CREATE INDEX fki_estado ON tb_ciudad USING btree (codigo);
    DROP INDEX public.fki_estado;
       public         postgres    false    181            �           1259    19363    fki_noticia_sistema    INDEX     M   CREATE INDEX fki_noticia_sistema ON tb_noticia USING btree (codigo_sistema);
 '   DROP INDEX public.fki_noticia_sistema;
       public         postgres    false    215            �           1259    19365    fki_organizacion_esteticista    INDEX     _   CREATE INDEX fki_organizacion_esteticista ON tb_esteticista USING btree (codigo_organizacion);
 0   DROP INDEX public.fki_organizacion_esteticista;
       public         postgres    false    197            �           1259    19366    fki_paquete    INDEX     G   CREATE INDEX fki_paquete ON tb_promocion USING btree (codigo_paquete);
    DROP INDEX public.fki_paquete;
       public         postgres    false    228            	           1259    19367    fki_paquete_sesion    INDEX     W   CREATE INDEX fki_paquete_sesion ON tb_sesion_por_paquete USING btree (codigo_paquete);
 &   DROP INDEX public.fki_paquete_sesion;
       public         postgres    false    236            (	           1259    19368    fki_rol    INDEX     :   CREATE INDEX fki_rol ON tb_usuario USING btree (usuario);
    DROP INDEX public.fki_rol;
       public         postgres    false    252            �           1259    19369    fki_servicio_antecedente_codigo    INDEX     g   CREATE INDEX fki_servicio_antecedente_codigo ON tb_antecedente_servicio USING btree (codigo_servicio);
 3   DROP INDEX public.fki_servicio_antecedente_codigo;
       public         postgres    false    175            
	           1259    19370    fki_slider_sistema    INDEX     K   CREATE INDEX fki_slider_sistema ON tb_slider USING btree (codigo_sistema);
 &   DROP INDEX public.fki_slider_sistema;
       public         postgres    false    238            u           1259    19371    fki_tipo_acuerdo    INDEX     H   CREATE INDEX fki_tipo_acuerdo ON tb_acuerdo USING btree (tipo_acuerdo);
 $   DROP INDEX public.fki_tipo_acuerdo;
       public         postgres    false    171            �           1259    19372    fki_tipo_incidencia    INDEX     Q   CREATE INDEX fki_tipo_incidencia ON tb_incidencia USING btree (tipo_incidencia);
 '   DROP INDEX public.fki_tipo_incidencia;
       public         postgres    false    207            �           1259    19373    fki_tipo_red_social    INDEX     Q   CREATE INDEX fki_tipo_red_social ON tb_red_social USING btree (tipo_red_social);
 '   DROP INDEX public.fki_tipo_red_social;
       public         postgres    false    229            	           1259    19374    fki_tipo_servicio    INDEX     K   CREATE INDEX fki_tipo_servicio ON tb_servicio USING btree (tipo_servicio);
 %   DROP INDEX public.fki_tipo_servicio;
       public         postgres    false    235            9	           2606    19375    fk_agenda_cita    FK CONSTRAINT     u   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_agenda_cita FOREIGN KEY (codigo_agenda) REFERENCES tb_agenda(codigo);
 @   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_agenda_cita;
       public       postgres    false    180    172    2169            2	           2606    19380    fk_antecedente_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_antecedente_cliente
    ADD CONSTRAINT fk_antecedente_cliente FOREIGN KEY (codigo_antecedente) REFERENCES tb_antecedente(codigo);
 W   ALTER TABLE ONLY public.tb_antecedente_cliente DROP CONSTRAINT fk_antecedente_cliente;
       public       postgres    false    173    174    2171            4	           2606    19385    fk_antecedente_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_antecedente_servicio
    ADD CONSTRAINT fk_antecedente_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_antecedente(codigo);
 Y   ALTER TABLE ONLY public.tb_antecedente_servicio DROP CONSTRAINT fk_antecedente_servicio;
       public       postgres    false    175    173    2171            6	           2606    19390    fk_avance_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_avance_servicio
    ADD CONSTRAINT fk_avance_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_avance(codigo);
 O   ALTER TABLE ONLY public.tb_avance_servicio DROP CONSTRAINT fk_avance_servicio;
       public       postgres    false    176    2181    177            T	           2606    19395    fk_avance_sesion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_sesion
    ADD CONSTRAINT fk_avance_sesion FOREIGN KEY (codigo_avance) REFERENCES tb_avance(codigo);
 L   ALTER TABLE ONLY public.tb_detalle_sesion DROP CONSTRAINT fk_avance_sesion;
       public       postgres    false    2181    176    190            j	           2606    19400 	   fk_bloque    FK CONSTRAINT     s   ALTER TABLE ONLY tb_horario
    ADD CONSTRAINT fk_bloque FOREIGN KEY (codigo_bloque) REFERENCES tb_bloque(codigo);
 >   ALTER TABLE ONLY public.tb_horario DROP CONSTRAINT fk_bloque;
       public       postgres    false    204    178    2185            Z	           2606    19405    fk_cita_diagnostico    FK CONSTRAINT     }   ALTER TABLE ONLY tb_diagnostico
    ADD CONSTRAINT fk_cita_diagnostico FOREIGN KEY (codigo_cita) REFERENCES tb_cita(codigo);
 L   ALTER TABLE ONLY public.tb_diagnostico DROP CONSTRAINT fk_cita_diagnostico;
       public       postgres    false    180    193    2191            @	           2606    19410    fk_ciudad_cliente    FK CONSTRAINT     t   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_ciudad_cliente FOREIGN KEY (ciudad) REFERENCES tb_ciudad(codigo);
 F   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_ciudad_cliente;
       public       postgres    false    2194    181    182            �	           2606    19415    fk_ciudad_usuario_web    FK CONSTRAINT     �   ALTER TABLE ONLY tb_usuario_web
    ADD CONSTRAINT fk_ciudad_usuario_web FOREIGN KEY (codigo_ciudad) REFERENCES tb_ciudad(codigo);
 N   ALTER TABLE ONLY public.tb_usuario_web DROP CONSTRAINT fk_ciudad_usuario_web;
       public       postgres    false    253    181    2194            3	           2606    19420    fk_cliente_antecedente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_antecedente_cliente
    ADD CONSTRAINT fk_cliente_antecedente FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 W   ALTER TABLE ONLY public.tb_antecedente_cliente DROP CONSTRAINT fk_cliente_antecedente;
       public       postgres    false    174    2196    182            b	           2606    19425    fk_cliente_formulario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario_cliente
    ADD CONSTRAINT fk_cliente_formulario FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 U   ALTER TABLE ONLY public.tb_formulario_cliente DROP CONSTRAINT fk_cliente_formulario;
       public       postgres    false    2196    199    182            f	           2606    19430    fk_cliente_habito    FK CONSTRAINT     �   ALTER TABLE ONLY tb_habito_cliente
    ADD CONSTRAINT fk_cliente_habito FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 M   ALTER TABLE ONLY public.tb_habito_cliente DROP CONSTRAINT fk_cliente_habito;
       public       postgres    false    202    182    2196            x	           2606    19435    fk_cliente_necesidad    FK CONSTRAINT     �   ALTER TABLE ONLY tb_necesidad_cliente
    ADD CONSTRAINT fk_cliente_necesidad FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 S   ALTER TABLE ONLY public.tb_necesidad_cliente DROP CONSTRAINT fk_cliente_necesidad;
       public       postgres    false    2196    214    182            �	           2606    19440    fk_cliente_potencial    FK CONSTRAINT     �   ALTER TABLE ONLY tb_respuesta_formulario_web
    ADD CONSTRAINT fk_cliente_potencial FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 Z   ALTER TABLE ONLY public.tb_respuesta_formulario_web DROP CONSTRAINT fk_cliente_potencial;
       public       postgres    false    182    233    2196            �	           2606    19445    fk_cliente_preferencia    FK CONSTRAINT     �   ALTER TABLE ONLY tb_preferencia_cliente
    ADD CONSTRAINT fk_cliente_preferencia FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 W   ALTER TABLE ONLY public.tb_preferencia_cliente DROP CONSTRAINT fk_cliente_preferencia;
       public       postgres    false    2196    225    182            �	           2606    19450    fk_cliente_solicitud    FK CONSTRAINT     �   ALTER TABLE ONLY tb_solicitud
    ADD CONSTRAINT fk_cliente_solicitud FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 K   ALTER TABLE ONLY public.tb_solicitud DROP CONSTRAINT fk_cliente_solicitud;
       public       postgres    false    239    182    2196            A	           2606    19455    fk_codigo_acuerdo    FK CONSTRAINT     }   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_codigo_acuerdo FOREIGN KEY (codigo_acuerdo) REFERENCES tb_acuerdo(codigo);
 F   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_codigo_acuerdo;
       public       postgres    false    2167    182    171            n	           2606    19460    fk_codigo_agenda    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario_esteticista
    ADD CONSTRAINT fk_codigo_agenda FOREIGN KEY (codigo_agenda) REFERENCES tb_agenda(codigo);
 Q   ALTER TABLE ONLY public.tb_horario_esteticista DROP CONSTRAINT fk_codigo_agenda;
       public       postgres    false    172    206    2169            U	           2606    19465    fk_codigo_cita    FK CONSTRAINT     {   ALTER TABLE ONLY tb_detalle_sesion
    ADD CONSTRAINT fk_codigo_cita FOREIGN KEY (codigo_cita) REFERENCES tb_cita(codigo);
 J   ALTER TABLE ONLY public.tb_detalle_sesion DROP CONSTRAINT fk_codigo_cita;
       public       postgres    false    180    190    2191            d	           2606    19470    fk_codigo_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario_web_cliente
    ADD CONSTRAINT fk_codigo_cliente FOREIGN KEY (codigo_cliente) REFERENCES tb_cliente(cedula);
 U   ALTER TABLE ONLY public.tb_formulario_web_cliente DROP CONSTRAINT fk_codigo_cliente;
       public       postgres    false    182    2196    200            V	           2606    19475    fk_codigo_notificacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_sesion
    ADD CONSTRAINT fk_codigo_notificacion FOREIGN KEY (codigo_notificacion) REFERENCES tb_notificacion(codigo);
 R   ALTER TABLE ONLY public.tb_detalle_sesion DROP CONSTRAINT fk_codigo_notificacion;
       public       postgres    false    190    216    2267            B	           2606    19480    fk_codigo_ocupacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_codigo_ocupacion FOREIGN KEY (codigo_ocupacion) REFERENCES tb_ocupacion(codigo);
 H   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_codigo_ocupacion;
       public       postgres    false    2271    182    218            �	           2606    19485    fk_codigo_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_servicio
    ADD CONSTRAINT fk_codigo_organizacion FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 L   ALTER TABLE ONLY public.tb_servicio DROP CONSTRAINT fk_codigo_organizacion;
       public       postgres    false    221    235    2277            C	           2606    19490    fk_codigo_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_codigo_organizacion FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 K   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_codigo_organizacion;
       public       postgres    false    221    182    2277            D	           2606    19495    fk_codigo_referencia    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_codigo_referencia FOREIGN KEY (codigo_referencia) REFERENCES tb_referencia(codigo);
 I   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_codigo_referencia;
       public       postgres    false    230    182    2297            e	           2606    19500    fk_codigo_respuesta    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario_web_cliente
    ADD CONSTRAINT fk_codigo_respuesta FOREIGN KEY (codigo_respuesta) REFERENCES tb_respuesta(codigo);
 W   ALTER TABLE ONLY public.tb_formulario_web_cliente DROP CONSTRAINT fk_codigo_respuesta;
       public       postgres    false    231    200    2299            G	           2606    19505    fk_codigo_usuario_web    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario
    ADD CONSTRAINT fk_codigo_usuario_web FOREIGN KEY (codigo_usuario_web) REFERENCES tb_usuario_web(cedula);
 M   ALTER TABLE ONLY public.tb_comentario DROP CONSTRAINT fk_codigo_usuario_web;
       public       postgres    false    253    183    2348            J	           2606    19510    fk_comentario_cubiculo    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_cubiculo
    ADD CONSTRAINT fk_comentario_cubiculo FOREIGN KEY (codigo_comentario) REFERENCES tb_comentario(codigo);
 W   ALTER TABLE ONLY public.tb_comentario_cubiculo DROP CONSTRAINT fk_comentario_cubiculo;
       public       postgres    false    183    184    2198            L	           2606    19515    fk_comentario_esteticista    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_esteticista
    ADD CONSTRAINT fk_comentario_esteticista FOREIGN KEY (codigo_comentario) REFERENCES tb_comentario(codigo);
 ]   ALTER TABLE ONLY public.tb_comentario_esteticista DROP CONSTRAINT fk_comentario_esteticista;
       public       postgres    false    2198    183    185            N	           2606    19520    fk_comentario_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_organizacion
    ADD CONSTRAINT fk_comentario_organizacion FOREIGN KEY (codigo_comentario) REFERENCES tb_comentario(codigo);
 _   ALTER TABLE ONLY public.tb_comentario_organizacion DROP CONSTRAINT fk_comentario_organizacion;
       public       postgres    false    2198    186    183            �	           2606    19525    fk_comentario_respuesta    FK CONSTRAINT     �   ALTER TABLE ONLY tb_respuesta_comentario
    ADD CONSTRAINT fk_comentario_respuesta FOREIGN KEY (codigo_comentario) REFERENCES tb_comentario(codigo);
 Y   ALTER TABLE ONLY public.tb_respuesta_comentario DROP CONSTRAINT fk_comentario_respuesta;
       public       postgres    false    232    2198    183            P	           2606    19530    fk_comentario_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_servicio
    ADD CONSTRAINT fk_comentario_servicio FOREIGN KEY (codigo_comentario) REFERENCES tb_comentario(codigo);
 W   ALTER TABLE ONLY public.tb_comentario_servicio DROP CONSTRAINT fk_comentario_servicio;
       public       postgres    false    187    183    2198            E	           2606    19535    fk_correo_usuario    FK CONSTRAINT     v   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_correo_usuario FOREIGN KEY (correo) REFERENCES tb_usuario(usuario);
 F   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_correo_usuario;
       public       postgres    false    252    182    2346            :	           2606    19540    fk_cubiculo_cita    FK CONSTRAINT     {   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_cubiculo_cita FOREIGN KEY (codigo_cubiculo) REFERENCES tb_cubiculo(codigo);
 B   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_cubiculo_cita;
       public       postgres    false    188    2208    180            K	           2606    19545    fk_cubiculo_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_cubiculo
    ADD CONSTRAINT fk_cubiculo_comentario FOREIGN KEY (codigo_cubiculo) REFERENCES tb_cubiculo(codigo);
 W   ALTER TABLE ONLY public.tb_comentario_cubiculo DROP CONSTRAINT fk_cubiculo_comentario;
       public       postgres    false    2208    184    188            8	           2606    19550    fk_detalle_sesion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_calificacion
    ADD CONSTRAINT fk_detalle_sesion FOREIGN KEY (codigo_detalle_sesion) REFERENCES tb_detalle_sesion(codigo);
 K   ALTER TABLE ONLY public.tb_calificacion DROP CONSTRAINT fk_detalle_sesion;
       public       postgres    false    190    2212    179            r	           2606    19555    fk_detalle_sesion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_incidencia_sesion
    ADD CONSTRAINT fk_detalle_sesion FOREIGN KEY (codigo_detalle_sesion) REFERENCES tb_detalle_sesion(codigo);
 P   ALTER TABLE ONLY public.tb_incidencia_sesion DROP CONSTRAINT fk_detalle_sesion;
       public       postgres    false    190    208    2212            ;	           2606    19560    fk_detalle_solicitud_cita    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_detalle_solicitud_cita FOREIGN KEY (codigo_detalle_solicitud) REFERENCES tb_detalle_solicitud(codigo);
 K   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_detalle_solicitud_cita;
       public       postgres    false    191    2214    180            k	           2606    19565    fk_dia_laborable    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario
    ADD CONSTRAINT fk_dia_laborable FOREIGN KEY (codigo_dia_laborable) REFERENCES tb_dia_laborable(codigo);
 E   ALTER TABLE ONLY public.tb_horario DROP CONSTRAINT fk_dia_laborable;
       public       postgres    false    204    192    2216            1	           2606    19570    fk_dia_laborable    FK CONSTRAINT     }   ALTER TABLE ONLY tb_agenda
    ADD CONSTRAINT fk_dia_laborable FOREIGN KEY (codigo_dia) REFERENCES tb_dia_laborable(codigo);
 D   ALTER TABLE ONLY public.tb_agenda DROP CONSTRAINT fk_dia_laborable;
       public       postgres    false    192    172    2216            t	           2606    19575    fk_diagnostico_indicador    FK CONSTRAINT     �   ALTER TABLE ONLY tb_indicador_diagnostico
    ADD CONSTRAINT fk_diagnostico_indicador FOREIGN KEY (codigo_diagnostico) REFERENCES tb_diagnostico(codigo);
 [   ALTER TABLE ONLY public.tb_indicador_diagnostico DROP CONSTRAINT fk_diagnostico_indicador;
       public       postgres    false    210    193    2218            l	           2606    19580    fk_equipo_horario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario_equipo
    ADD CONSTRAINT fk_equipo_horario FOREIGN KEY (codigo_equipo) REFERENCES tb_equipo(codigo);
 M   ALTER TABLE ONLY public.tb_horario_equipo DROP CONSTRAINT fk_equipo_horario;
       public       postgres    false    205    2220    194            [	           2606    19585    fk_equipo_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_equipo_servicio
    ADD CONSTRAINT fk_equipo_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_equipo(codigo);
 O   ALTER TABLE ONLY public.tb_equipo_servicio DROP CONSTRAINT fk_equipo_servicio;
       public       postgres    false    194    2220    195            ?	           2606    19590    fk_estado_ciudad    FK CONSTRAINT     r   ALTER TABLE ONLY tb_ciudad
    ADD CONSTRAINT fk_estado_ciudad FOREIGN KEY (estado) REFERENCES tb_estado(codigo);
 D   ALTER TABLE ONLY public.tb_ciudad DROP CONSTRAINT fk_estado_ciudad;
       public       postgres    false    196    181    2224            ]	           2606    19595    fk_estado_esteticista    FK CONSTRAINT     �   ALTER TABLE ONLY tb_esteticista
    ADD CONSTRAINT fk_estado_esteticista FOREIGN KEY (codigo_estado) REFERENCES tb_estado(codigo);
 N   ALTER TABLE ONLY public.tb_esteticista DROP CONSTRAINT fk_estado_esteticista;
       public       postgres    false    197    2224    196            �	           2606    19600    fk_estado_perfil    FK CONSTRAINT     �   ALTER TABLE ONLY tb_perfil_usuario
    ADD CONSTRAINT fk_estado_perfil FOREIGN KEY (codigo_estado) REFERENCES tb_estado(codigo);
 L   ALTER TABLE ONLY public.tb_perfil_usuario DROP CONSTRAINT fk_estado_perfil;
       public       postgres    false    196    223    2224            <	           2606    19605    fk_esteticista    FK CONSTRAINT        ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_esteticista FOREIGN KEY (codigo_esteticista) REFERENCES tb_esteticista(cedula);
 @   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_esteticista;
       public       postgres    false    180    2227    197            M	           2606    19610    fk_esteticista_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_esteticista
    ADD CONSTRAINT fk_esteticista_comentario FOREIGN KEY (codigo_esteticista) REFERENCES tb_esteticista(cedula);
 ]   ALTER TABLE ONLY public.tb_comentario_esteticista DROP CONSTRAINT fk_esteticista_comentario;
       public       postgres    false    185    197    2227            o	           2606    19615    fk_esteticista_horario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario_esteticista
    ADD CONSTRAINT fk_esteticista_horario FOREIGN KEY (codigo_esteticista) REFERENCES tb_esteticista(cedula);
 W   ALTER TABLE ONLY public.tb_horario_esteticista DROP CONSTRAINT fk_esteticista_horario;
       public       postgres    false    197    2227    206            ^	           2606    19620    fk_esteticista_usuario    FK CONSTRAINT        ALTER TABLE ONLY tb_esteticista
    ADD CONSTRAINT fk_esteticista_usuario FOREIGN KEY (correo) REFERENCES tb_usuario(usuario);
 O   ALTER TABLE ONLY public.tb_esteticista DROP CONSTRAINT fk_esteticista_usuario;
       public       postgres    false    197    2346    252            c	           2606    19625    fk_formulario_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario_cliente
    ADD CONSTRAINT fk_formulario_cliente FOREIGN KEY (codigo_formulario) REFERENCES tb_formulario(codigo);
 U   ALTER TABLE ONLY public.tb_formulario_cliente DROP CONSTRAINT fk_formulario_cliente;
       public       postgres    false    199    2229    198            g	           2606    19630    fk_habito_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_habito_cliente
    ADD CONSTRAINT fk_habito_cliente FOREIGN KEY (codigo_habito) REFERENCES tb_habito(codigo);
 M   ALTER TABLE ONLY public.tb_habito_cliente DROP CONSTRAINT fk_habito_cliente;
       public       postgres    false    201    2235    202            h	           2606    19635    fk_habito_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_habito_servicio
    ADD CONSTRAINT fk_habito_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_habito(codigo);
 O   ALTER TABLE ONLY public.tb_habito_servicio DROP CONSTRAINT fk_habito_servicio;
       public       postgres    false    201    2235    203            m	           2606    19640    fk_horario_equipo    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario_equipo
    ADD CONSTRAINT fk_horario_equipo FOREIGN KEY (codigo_horario) REFERENCES tb_horario(codigo);
 M   ALTER TABLE ONLY public.tb_horario_equipo DROP CONSTRAINT fk_horario_equipo;
       public       postgres    false    205    204    2241            p	           2606    19645    fk_horario_esteticista    FK CONSTRAINT     �   ALTER TABLE ONLY tb_horario_esteticista
    ADD CONSTRAINT fk_horario_esteticista FOREIGN KEY (codigo_horario) REFERENCES tb_horario(codigo);
 W   ALTER TABLE ONLY public.tb_horario_esteticista DROP CONSTRAINT fk_horario_esteticista;
       public       postgres    false    2241    206    204            s	           2606    19650    fk_incidencia_sesion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_incidencia_sesion
    ADD CONSTRAINT fk_incidencia_sesion FOREIGN KEY (codigo_incidencia) REFERENCES tb_incidencia(codigo);
 S   ALTER TABLE ONLY public.tb_incidencia_sesion DROP CONSTRAINT fk_incidencia_sesion;
       public       postgres    false    207    2248    208            u	           2606    19655    fk_indicador_diagnostico    FK CONSTRAINT     �   ALTER TABLE ONLY tb_indicador_diagnostico
    ADD CONSTRAINT fk_indicador_diagnostico FOREIGN KEY (codigo_indicador) REFERENCES tb_indicador(codigo);
 [   ALTER TABLE ONLY public.tb_indicador_diagnostico DROP CONSTRAINT fk_indicador_diagnostico;
       public       postgres    false    209    2252    210            v	           2606    19660    fk_indicador_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_indicador_servicio
    ADD CONSTRAINT fk_indicador_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_indicador(codigo);
 U   ALTER TABLE ONLY public.tb_indicador_servicio DROP CONSTRAINT fk_indicador_servicio;
       public       postgres    false    2252    209    211            =	           2606    19665    fk_motivo_cancelacion_cita    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_motivo_cancelacion_cita FOREIGN KEY (codigo_motivo_cancelacion) REFERENCES tb_motivo_cancelacion(codigo);
 L   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_motivo_cancelacion_cita;
       public       postgres    false    212    2258    180            y	           2606    19670    fk_noticia_sistema    FK CONSTRAINT     ~   ALTER TABLE ONLY tb_noticia
    ADD CONSTRAINT fk_noticia_sistema FOREIGN KEY (codigo_sistema) REFERENCES tb_sistema(codigo);
 G   ALTER TABLE ONLY public.tb_noticia DROP CONSTRAINT fk_noticia_sistema;
       public       postgres    false    2313    237    215            >	           2606    19675    fk_notificacion_cita    FK CONSTRAINT     �   ALTER TABLE ONLY tb_cita
    ADD CONSTRAINT fk_notificacion_cita FOREIGN KEY (codigo_notificacion) REFERENCES tb_notificacion(codigo);
 F   ALTER TABLE ONLY public.tb_cita DROP CONSTRAINT fk_notificacion_cita;
       public       postgres    false    2267    216    180            }	           2606    19680    fk_ocupacion_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_ocupacion_servicio
    ADD CONSTRAINT fk_ocupacion_servicio FOREIGN KEY (codigo_maestro) REFERENCES tb_ocupacion(codigo);
 U   ALTER TABLE ONLY public.tb_ocupacion_servicio DROP CONSTRAINT fk_ocupacion_servicio;
       public       postgres    false    218    2271    219            �	           2606    19690    fk_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_perfil_usuario
    ADD CONSTRAINT fk_organizacion FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 K   ALTER TABLE ONLY public.tb_perfil_usuario DROP CONSTRAINT fk_organizacion;
       public       postgres    false    221    2277    223            |	           2606    19695    fk_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_objetivo
    ADD CONSTRAINT fk_organizacion FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 E   ALTER TABLE ONLY public.tb_objetivo DROP CONSTRAINT fk_organizacion;
       public       postgres    false    221    217    2277            /	           2606    19700    fk_organizacion_acuerdo    FK CONSTRAINT     �   ALTER TABLE ONLY tb_acuerdo
    ADD CONSTRAINT fk_organizacion_acuerdo FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 L   ALTER TABLE ONLY public.tb_acuerdo DROP CONSTRAINT fk_organizacion_acuerdo;
       public       postgres    false    171    2277    221            O	           2606    19705    fk_organizacion_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_organizacion
    ADD CONSTRAINT fk_organizacion_comentario FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 _   ALTER TABLE ONLY public.tb_comentario_organizacion DROP CONSTRAINT fk_organizacion_comentario;
       public       postgres    false    186    221    2277            _	           2606    19710    fk_organizacion_esteticista    FK CONSTRAINT     �   ALTER TABLE ONLY tb_esteticista
    ADD CONSTRAINT fk_organizacion_esteticista FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 T   ALTER TABLE ONLY public.tb_esteticista DROP CONSTRAINT fk_organizacion_esteticista;
       public       postgres    false    221    2277    197            �	           2606    19715    fk_organizacion_red_social    FK CONSTRAINT     �   ALTER TABLE ONLY tb_red_social
    ADD CONSTRAINT fk_organizacion_red_social FOREIGN KEY (codigo_organizacion) REFERENCES tb_organizacion(rif);
 R   ALTER TABLE ONLY public.tb_red_social DROP CONSTRAINT fk_organizacion_red_social;
       public       postgres    false    2277    229    221            �	           2606    19720    fk_paquete_promocion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_promocion
    ADD CONSTRAINT fk_paquete_promocion FOREIGN KEY (codigo_paquete) REFERENCES tb_paquete(codigo);
 K   ALTER TABLE ONLY public.tb_promocion DROP CONSTRAINT fk_paquete_promocion;
       public       postgres    false    2279    228    222            R	           2606    19725    fk_paquete_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_paquete
    ADD CONSTRAINT fk_paquete_servicio FOREIGN KEY (codigo_paquete) REFERENCES tb_paquete(codigo);
 P   ALTER TABLE ONLY public.tb_detalle_paquete DROP CONSTRAINT fk_paquete_servicio;
       public       postgres    false    2279    222    189            �	           2606    19730    fk_paquete_sesion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_sesion_por_paquete
    ADD CONSTRAINT fk_paquete_sesion FOREIGN KEY (codigo_paquete) REFERENCES tb_paquete(codigo);
 Q   ALTER TABLE ONLY public.tb_sesion_por_paquete DROP CONSTRAINT fk_paquete_sesion;
       public       postgres    false    222    236    2279            W	           2606    19735    fk_paquete_solicitud    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_solicitud
    ADD CONSTRAINT fk_paquete_solicitud FOREIGN KEY (codigo_paquete) REFERENCES tb_paquete(codigo);
 S   ALTER TABLE ONLY public.tb_detalle_solicitud DROP CONSTRAINT fk_paquete_solicitud;
       public       postgres    false    191    2279    222            �	           2606    19740    fk_perfil_usuario    FK CONSTRAINT     }   ALTER TABLE ONLY tb_perfil_usuario
    ADD CONSTRAINT fk_perfil_usuario FOREIGN KEY (correo) REFERENCES tb_usuario(usuario);
 M   ALTER TABLE ONLY public.tb_perfil_usuario DROP CONSTRAINT fk_perfil_usuario;
       public       postgres    false    2346    223    252            �	           2606    19745    fk_preferencia_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY tb_preferencia_cliente
    ADD CONSTRAINT fk_preferencia_cliente FOREIGN KEY (codigo_preferencia) REFERENCES tb_preferencia(codigo);
 W   ALTER TABLE ONLY public.tb_preferencia_cliente DROP CONSTRAINT fk_preferencia_cliente;
       public       postgres    false    224    225    2283            �	           2606    19750    fk_preferencia_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_preferencia_servicio
    ADD CONSTRAINT fk_preferencia_servicio FOREIGN KEY (codigo_preferencia) REFERENCES tb_preferencia(codigo);
 Y   ALTER TABLE ONLY public.tb_preferencia_servicio DROP CONSTRAINT fk_preferencia_servicio;
       public       postgres    false    226    2283    224            `	           2606    19755    fk_pregunta_formulario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario
    ADD CONSTRAINT fk_pregunta_formulario FOREIGN KEY (codigo_pregunta) REFERENCES tb_pregunta(codigo);
 N   ALTER TABLE ONLY public.tb_formulario DROP CONSTRAINT fk_pregunta_formulario;
       public       postgres    false    198    2289    227            a	           2606    19760    fk_respuesta_formulario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_formulario
    ADD CONSTRAINT fk_respuesta_formulario FOREIGN KEY (codigo_respuesta) REFERENCES tb_respuesta(codigo);
 O   ALTER TABLE ONLY public.tb_formulario DROP CONSTRAINT fk_respuesta_formulario;
       public       postgres    false    2299    198    231            �	           2606    19765    fk_rol    FK CONSTRAINT     c   ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT fk_rol FOREIGN KEY (rol) REFERENCES tb_rol(codigo);
 ;   ALTER TABLE ONLY public.tb_usuario DROP CONSTRAINT fk_rol;
       public       postgres    false    234    252    2305            7	           2606    19770    fk_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_avance_servicio
    ADD CONSTRAINT fk_servicio FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 H   ALTER TABLE ONLY public.tb_avance_servicio DROP CONSTRAINT fk_servicio;
       public       postgres    false    235    2308    177            5	           2606    19775    fk_servicio_antecedente_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY tb_antecedente_servicio
    ADD CONSTRAINT fk_servicio_antecedente_codigo FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 `   ALTER TABLE ONLY public.tb_antecedente_servicio DROP CONSTRAINT fk_servicio_antecedente_codigo;
       public       postgres    false    2308    175    235            Q	           2606    19780    fk_servicio_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario_servicio
    ADD CONSTRAINT fk_servicio_comentario FOREIGN KEY (codigo_servicio) REFERENCES tb_tipo_servicio(codigo);
 W   ALTER TABLE ONLY public.tb_comentario_servicio DROP CONSTRAINT fk_servicio_comentario;
       public       postgres    false    187    251    2343            \	           2606    19785    fk_servicio_equipo    FK CONSTRAINT     �   ALTER TABLE ONLY tb_equipo_servicio
    ADD CONSTRAINT fk_servicio_equipo FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 O   ALTER TABLE ONLY public.tb_equipo_servicio DROP CONSTRAINT fk_servicio_equipo;
       public       postgres    false    195    235    2308            i	           2606    19790    fk_servicio_habito    FK CONSTRAINT     �   ALTER TABLE ONLY tb_habito_servicio
    ADD CONSTRAINT fk_servicio_habito FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 O   ALTER TABLE ONLY public.tb_habito_servicio DROP CONSTRAINT fk_servicio_habito;
       public       postgres    false    203    2308    235            w	           2606    19795    fk_servicio_indicador    FK CONSTRAINT     �   ALTER TABLE ONLY tb_indicador_servicio
    ADD CONSTRAINT fk_servicio_indicador FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 U   ALTER TABLE ONLY public.tb_indicador_servicio DROP CONSTRAINT fk_servicio_indicador;
       public       postgres    false    211    2308    235            ~	           2606    19800    fk_servicio_ocupacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_ocupacion_servicio
    ADD CONSTRAINT fk_servicio_ocupacion FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 U   ALTER TABLE ONLY public.tb_ocupacion_servicio DROP CONSTRAINT fk_servicio_ocupacion;
       public       postgres    false    219    2308    235            S	           2606    19805    fk_servicio_paquete    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_paquete
    ADD CONSTRAINT fk_servicio_paquete FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 P   ALTER TABLE ONLY public.tb_detalle_paquete DROP CONSTRAINT fk_servicio_paquete;
       public       postgres    false    189    2308    235            �	           2606    19810    fk_servicio_preferencia    FK CONSTRAINT     �   ALTER TABLE ONLY tb_preferencia_servicio
    ADD CONSTRAINT fk_servicio_preferencia FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 Y   ALTER TABLE ONLY public.tb_preferencia_servicio DROP CONSTRAINT fk_servicio_preferencia;
       public       postgres    false    2308    235    226            X	           2606    19815    fk_servicio_solicitud    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_solicitud
    ADD CONSTRAINT fk_servicio_solicitud FOREIGN KEY (codigo_servicio) REFERENCES tb_servicio(codigo);
 T   ALTER TABLE ONLY public.tb_detalle_solicitud DROP CONSTRAINT fk_servicio_solicitud;
       public       postgres    false    191    2308    235            �	           2606    19820    fk_slider_sistema    FK CONSTRAINT     |   ALTER TABLE ONLY tb_slider
    ADD CONSTRAINT fk_slider_sistema FOREIGN KEY (codigo_sistema) REFERENCES tb_sistema(codigo);
 E   ALTER TABLE ONLY public.tb_slider DROP CONSTRAINT fk_slider_sistema;
       public       postgres    false    237    2313    238            Y	           2606    19825    fk_solicitud    FK CONSTRAINT     �   ALTER TABLE ONLY tb_detalle_solicitud
    ADD CONSTRAINT fk_solicitud FOREIGN KEY (codigo_solicitud) REFERENCES tb_solicitud(codigo);
 K   ALTER TABLE ONLY public.tb_detalle_solicitud DROP CONSTRAINT fk_solicitud;
       public       postgres    false    239    2319    191            0	           2606    19830    fk_tipo_acuerdo    FK CONSTRAINT     ~   ALTER TABLE ONLY tb_acuerdo
    ADD CONSTRAINT fk_tipo_acuerdo FOREIGN KEY (tipo_acuerdo) REFERENCES tb_tipo_acuerdo(codigo);
 D   ALTER TABLE ONLY public.tb_acuerdo DROP CONSTRAINT fk_tipo_acuerdo;
       public       postgres    false    240    2321    171            F	           2606    19835    fk_tipo_cliente    FK CONSTRAINT     ~   ALTER TABLE ONLY tb_cliente
    ADD CONSTRAINT fk_tipo_cliente FOREIGN KEY (tipo_cliente) REFERENCES tb_tipo_cliente(codigo);
 D   ALTER TABLE ONLY public.tb_cliente DROP CONSTRAINT fk_tipo_cliente;
       public       postgres    false    241    182    2323            H	           2606    19840    fk_tipo_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario
    ADD CONSTRAINT fk_tipo_comentario FOREIGN KEY (tipo_comentario) REFERENCES tb_tipo_comentario(codigo);
 J   ALTER TABLE ONLY public.tb_comentario DROP CONSTRAINT fk_tipo_comentario;
       public       postgres    false    2325    183    242            q	           2606    19845    fk_tipo_incidencia    FK CONSTRAINT     �   ALTER TABLE ONLY tb_incidencia
    ADD CONSTRAINT fk_tipo_incidencia FOREIGN KEY (tipo_incidencia) REFERENCES tb_tipo_incidencia(codigo);
 J   ALTER TABLE ONLY public.tb_incidencia DROP CONSTRAINT fk_tipo_incidencia;
       public       postgres    false    2329    244    207            z	           2606    19850    fk_tipo_noticia    FK CONSTRAINT     ~   ALTER TABLE ONLY tb_noticia
    ADD CONSTRAINT fk_tipo_noticia FOREIGN KEY (tipo_noticia) REFERENCES tb_tipo_noticia(codigo);
 D   ALTER TABLE ONLY public.tb_noticia DROP CONSTRAINT fk_tipo_noticia;
       public       postgres    false    245    2331    215            {	           2606    19855    fk_tipo_notificacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_notificacion
    ADD CONSTRAINT fk_tipo_notificacion FOREIGN KEY (tipo_notificacion) REFERENCES tb_tipo_notificacion(codigo);
 N   ALTER TABLE ONLY public.tb_notificacion DROP CONSTRAINT fk_tipo_notificacion;
       public       postgres    false    246    2333    216            	           2606    19860    fk_tipo_organizacion    FK CONSTRAINT     �   ALTER TABLE ONLY tb_organizacion
    ADD CONSTRAINT fk_tipo_organizacion FOREIGN KEY (tipo_organizacion) REFERENCES tb_tipo_organizacion(codigo);
 N   ALTER TABLE ONLY public.tb_organizacion DROP CONSTRAINT fk_tipo_organizacion;
       public       postgres    false    247    221    2335            �	           2606    19865    fk_tipo_preferencia    FK CONSTRAINT     �   ALTER TABLE ONLY tb_preferencia
    ADD CONSTRAINT fk_tipo_preferencia FOREIGN KEY (tipo_preferencia) REFERENCES tb_tipo_preferencia(codigo);
 L   ALTER TABLE ONLY public.tb_preferencia DROP CONSTRAINT fk_tipo_preferencia;
       public       postgres    false    224    248    2337            �	           2606    19870    fk_tipo_pregunta    FK CONSTRAINT     �   ALTER TABLE ONLY tb_pregunta
    ADD CONSTRAINT fk_tipo_pregunta FOREIGN KEY (tipo_pregunta) REFERENCES tb_tipo_pregunta(codigo);
 F   ALTER TABLE ONLY public.tb_pregunta DROP CONSTRAINT fk_tipo_pregunta;
       public       postgres    false    227    2339    249            �	           2606    19875    fk_tipo_red    FK CONSTRAINT     �   ALTER TABLE ONLY tb_red_social
    ADD CONSTRAINT fk_tipo_red FOREIGN KEY (tipo_red_social) REFERENCES tb_tipo_red_social(codigo);
 C   ALTER TABLE ONLY public.tb_red_social DROP CONSTRAINT fk_tipo_red;
       public       postgres    false    2341    250    229            �	           2606    19880    fk_tipo_servicio    FK CONSTRAINT     �   ALTER TABLE ONLY tb_servicio
    ADD CONSTRAINT fk_tipo_servicio FOREIGN KEY (tipo_servicio) REFERENCES tb_tipo_servicio(codigo);
 F   ALTER TABLE ONLY public.tb_servicio DROP CONSTRAINT fk_tipo_servicio;
       public       postgres    false    2343    251    235            I	           2606    19885    fk_usuario_comentario    FK CONSTRAINT     �   ALTER TABLE ONLY tb_comentario
    ADD CONSTRAINT fk_usuario_comentario FOREIGN KEY (codigo_usuario) REFERENCES tb_usuario(usuario);
 M   ALTER TABLE ONLY public.tb_comentario DROP CONSTRAINT fk_usuario_comentario;
       public       postgres    false    2346    183    252            �	           2606    27096     tb_opcion_rol_codigo_opcion_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY tb_opcion_rol
    ADD CONSTRAINT tb_opcion_rol_codigo_opcion_fkey FOREIGN KEY (codigo_opcion) REFERENCES tb_opcion(codigo);
 X   ALTER TABLE ONLY public.tb_opcion_rol DROP CONSTRAINT tb_opcion_rol_codigo_opcion_fkey;
       public       postgres    false    2275    220    254            �	           2606    27101    tb_opcion_rol_codigo_rol_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY tb_opcion_rol
    ADD CONSTRAINT tb_opcion_rol_codigo_rol_fkey FOREIGN KEY (codigo_rol) REFERENCES tb_rol(codigo);
 U   ALTER TABLE ONLY public.tb_opcion_rol DROP CONSTRAINT tb_opcion_rol_codigo_rol_fkey;
       public       postgres    false    254    234    2305            
   j   x���v
Q���W(I�OL.M-J�Ws�	uV�P7 Cu�����Ģ�0.�elhjb``fd�9&�d��Y~�>>RӚ˓�@��+s|J��I��� b�2}      
   z   x���v
Q���W(I�OLO�KITs�	uV�P7 Cuu��̲|0�$� 3/��$S�'�8�$Q�i��I�L#�f��1"�c��Ì1&��Ƙ��1!�S�Ƙ1�� B�a�      
   �   x���M�0����U�������Lq�U�.j�kg?��m����!���>�IY���eu>�kn\9�b(p���������	���h$�6ιp4h6�d埘y��8Xj5PX�U�_���d�EU"~e���6$��D��s�z4�M=z��JrZ�N�D�:ʎ�"�WzB	6أF��-�޴J�s      
   �   x���v
Q���W(I�O�+IMNMIR��9� Z!��'�5XAC� �u�-�̌���Al��crIfY���5�'YfZ����4��L#lfSf��L0��iB��&Hf��4��L�v#cK#S��fT�#t3�Q��� �\��      
   
   x���          
   
   x���          
   
   x���          	
   �   x���v
Q���W(I�O��/,MUs�	uV�P7 #u����Ҽ�|�������������������#��K2���5��<	�h��Z��Z��BS�Zh��\�XT�ˇfԵ��#�(3��I��� �xp�      

   
   x���          
   l   x���v
Q���W(I�O�,ITs�	uV�P7 Cu8�/����`8&�d��c*E���\���2�`L{��	�ˈzv� �Ic��˘zv�
C��eN=��� Uɱu      
   �   x���M�0��b7:���,,3�:���P��>��!���x���؂(�OgD�#i�+�2���^��1��F�L}F����5/Y��}7��0����?ca����6��1�(~����`��P�&���P�+��Gl%d�٥�H����R(XEyO8J����.ˑ�
�����c�*�i|?/��!h��ݲ      
   �  x����o�0���W�m ��>��!^c���������R���H�_��Na�иҢN:O��?�����ד��)���ސ~��ѭ2�"�N���'��1����9�Z����W�ԓ7>��2��ޞX�+L��\��.x���L�u��M��>���J��������1`c���̙�)-��Ӧoڭ��yF����G�cl�	9��ZJӻp�+e�~��	(+^�lN9IXIkH#�V�9y$\�Rc��h�-Dڪ�(�Om�����^�~���iT�ܷ���/�k���9���,�Wd;r����ﶝ���ǳP�S����u�X�� ��Z1�H���6��1Z<Ep�s3�U���_7��e!75��iR�y�z�۶��ny���z�Q��ʁ�'$�U9'�֨�U��^��,3
�i�yɋ*T�Ԯd8�d--�?&�rQs
�_�k2u�Bj���I�A�K;��UaMa�FX�b��	h㍳�I�(p�˪ޔ+r�̔n�&�X�dOw�5��E^	��煖Qt�%��(��X�C�J9�l>LB�}����o$��I��Q(���Zi��SLb��s�п�Ý~1�q
v�%��M��7�<��sm ����f>64>��q�zj��y��71J��-��%ޤ��`W�""��K��� P�&8KHr�I��~�}���xY������i"S�JĎ��⑌r��=��P D�a���	5�l����#z�>�lEƒ���P�,p�q}숷S&Y�B�ۣN,��c��v_PD�|YY�*��᭧f>H�T��`��8����g���%(Ek�&+�cK�:�(�Bԃ�\c�?�ơ>I~1_��Jcyw*`�)�/�=�`_�8n��r��fr�ğ+xwC�W�z�      
   �   x�ŒAK�0����6�&��Ë;�P���U��V��$���sx�pcN$!�K�����n�~�"͈�K������b���&W���waQ�����t����%$�.]����|#M��FA�:<7����Db3������e�^�e͉b�D�A	M�X����E5�U~�V�30"����9S�;����w6��~R햂E�&�7�,�������F���6DuD"Q��O�W\�K�,?�g�ȲO�o��      
   
   x���          
   
   x���          
   
   x���          
   
   x���          
   n   x���v
Q���W(I�O.M�L.��Ws�	uV�P7 #uu���Ĭ�b�1�$�,_]Ӛ˓� =E�yə�9d`�R�X�����W�_���X�Z�j �0;�      
   
   x���          
   
   x���          
   c   x���v
Q���W(I�OI-I��I�/���L�,)MQs�	uV�P7 CuT���crIfY���5�'I��6׈*���0cJ�5�m�	��\\ <�\      
   s   x���v
Q���W(I�O�L��IL�/JL�IUs�	uV�P�)�K-V�QP7 C�1�$�,_]Ӛ˓(|�JF�eDfjQr~�crL�*M-CaB�a@� ��)�\\ U�c�      
   
   x���          
   �   x���=�0�=��(8���S�"��TW��!�kSK��o�n.r7��/�Jt�f���
���Okk��r�5L伿���<�Lx������Q��t�6mŧ� N��D|�z���-?b;�V���!��9�Q��ȶ� RG�m7A��Vo6>�,�
�Uў�      
   n   x���v
Q���W(I�O-,�,ȏ/N-*�L��Ws�	uV�P7 Cu0��02�B}|4��<I0�� �2�鷤�E&��"Sd��C���`�M�j �to      
   s   x���v
Q���W(I�O-.IL�Ws�	uV�P7 Cuu�ĢD�\�Y���i��IX�HG@~QIizij1���A:"�6'�V������9)?)�dݦ Q�9�h��� i�VY      
   U  x���MO�0����$4�i�t��6���+r[o�%~=I�MB� ���7������l��,�x��S��6hD)���lA.�!�)eqڿ"�'P���Q��Xc#���E+*/��F��ƕ*$�d^���%���f[�����}��ЉQiė/�Dâ ��}�Vy~y���;f�F�:����؏�h�S���U��rs��#�J���~;X���q%C��I���i+����.wr�}ܔqF��Ƚtm�w8tJ�i�xG�d��z�	)�9��u)4	)O��9h2EUb�ަ�v>磧�k�*��%c�K��ƥ�;���<�Z��_H���X�s      
   
   x���          
   
   x���          
   
   x���           
   �   x��Ͽ
�0�Oq[�;9�C��T��U��F�\I_O��l(..�[?��%i�sH����ݱP��?\�&b��B�@�,J�$��(�e�h�}<YL�Q�[�F��E��8���l�c���K�*Kғ�*��Z5;���uh�_��e��_�f����������E�Zua      !
   �   x���v
Q���W(I��HL�,ɏO��L�+IUs�	uV�P7 CuuCKCC#&���i��I�QFF���F�0���2�����7��������f�	YFc3ʔ��-L�,�4C�� ıt�      "
   n   x���v
Q���W(I��HL�,ɏ/N-*�L��Ws�	uV�P7 Cu0�� �8&�d��kZsy�c�	����� Ø
���8�<ÌQb�R�0S'"�� nu|�      #
   �   x���;�0F���l��м|�ԡC@*��*�b�@)�~�4�\�|��7.�㺾��uÅϯ��O�i��ޜom�+Q/'Ŗ��{�^lN��@T4@��1�ȃ(��@��@��}$89�C4qr,��/ɺ �	�S'(֤N$P�!N�bqk��XK� �Z�(�'@�6u��b���]�      $
   �   x���?�P���Oq7<��498a��AN�X��0,�,q�N?��L/�(�|S���צ?�/mw�v�ݛkkv�j�Wf�z�wa�Î����Ѻ�S�nY��l�d����VHV([Y�l�dŲ����VJV�Z���G��{�݃���=�{�݃���=�{�݃���=�{�݃���=�{��[�����~���a�?���!Y��Gd�{���www��w���}2��I���e?      %
   
   x���          &
   
   x���          '
   
   x���          (
   V   x���v
Q���W(I���K�LNL�/Rs�	uV�P7 Cuu�̼�ҢD{�1�$�,_]Ӛ˓(����R�sS���� %�      )
   
   x���          *
   
   x���          +
   
   x���          ,
   
   x���          -
   
   x���          .
   
   x���          /
   �   x����N�0��}
�
��v�4A��"�mW�)���#v����T\������S궫޶P��W��{`��:� �M��:�+m-�(�9��:�7N�̹�8R�#�P��~��_HYp�A����il ��.�����&f�~&�^�%��� ��������Ai{#z�!&�8�\ۭ�e��UR��h1���_�N4��$���`��x�����Ɠ9��L<BV9Lb�z�̮E�rp��      0
   
   x���          1
   u   x���v
Q���W(I��O.-HL���Ss�	uV�P7 Cuu��D��T����D07�$�,_]Ӛ˓(3�@�\R�J2�K�2���9#?-���& M���E�%�E�h.�� �KMT      2
   
   x���          3
   �  x���Kr�6@�s
�Tű���ʿɸʱ]�=[B2�HWŷ��j��� ��(�MnT" ����|}��zx�o�t�L�S|=�y�Z����|2�#�}��i����O���5�o�(�q�u��=��q�m?�(׬G��=���׸����^����1
�8��r~��/��@��!��:h��VPg	������D0����o�7�Vp����5N��#v1�I[ʨj�� �" ���dE鷖�q�tDb�rH�k��������H����<�l~���o��c�U�$[�H+���Jx����z@{���X��-`i+ؼ�B`��<�P���a��>�U"�8��1.-�4���m�[l�7��/��szX��%����������b�0��U���y
N�y��ax�gh�LP�����{3s`ؖ	4�/C���� �\f".��:�g�=2lXRa�l�)�T��"$�溈1mc�E�UL�Y��;&	ʉ.��������D�����K5��.�M.�WĂ���v����RY����Ք���� �H�e���ַ�)C1#R�R�<���f�ŝdֻp�!�>+���w4Rԉ��ҲF�%��/�Ֆ�d�V��^m(36�*.�wI'#Ǒ��uM�����|�5�|���i|f��(?U]e����"D��"
GY�V��Dފ�t��p3���3/��Q�_t��L6��"�Y�}[T9WfH�.,\|��jv����S�XEwhW)��v������l[��c��L�-Mb�_��.�(��C�i�kGJ�P�.e�j2qhR��|���غb)� �_� 71�lw֔:�f�[����]wxG�#���;Գg�߈ �,�?JY8\M+#|�,�#]��O5Q�l�Ȫ.�r�n,�F�� �"E3���EP��:fĎDO��F�f]�X�z1e�nl#$�՞�q�f����0Z�S�	�4���Nk��s~�i�L �bw+$Q�Z����ͪ^��Q���~�tWV<n��ؓ��Ω8.�X�{}���c��%~ԙ����!%�
Q/�TG��!��@�z�P<v�/n�;�;����t| C�4� Q��׳�S�/�4w�@��4���O�%5(��p>���j~N�I|��Nu�t�d����m�M'^'�Z�����2#]t�ٶs�!�g��/a���h:"'�QXM^Q�o!$3��A���ak�oilY>xb��؋pӎJ+-ľm򛅞�7���f�|��"���h�����~�KѕV�4C�3/��G~� �pl���~�ڗA�L�~؉���ɹ*�y��	4~��t�����'��_IvhK��3?7��:�ze�'A��;�����'��$an�r@	W>vy:[Џ//ZҌz�q�s���Wo�R�(t���_��n�}�]��,El�9M���g��?�b��=�8�i�k>}��_��      U
   �  x���=KQ��>�b�(Xd��*V)�`�6�(�H���h��B�Z3��}΅��ͧ�����{�]���WO��j�]��^M���x��������f�r�r�<��������u��S��a�6E���ޥ�l�
u�b�t�H�P��k�aͻv9�M�����K�K�7����E�Զ�2�яP��ː�]Li��M/$G�;�'92�=ɑ�f0�#�]#92m׈�]#92��H�L	0dW	0bו #v]v��u�5b�e׉]�]'v]v��u�ub�e׉]�]'v]v���ub7d׉ݐ]'vCv���b7d7�ݐ� vCv���b7d7�ݔ� vSv��M�b7e7�ݔ�$vSv��M�Mb7e7�ݔ�$vSv��-�Mb�d7�ݒ�$vKv��-�-b�d�����[D])EԕPH�PH�P�KS��tF�xiJ@#^��~��F�w[�+�      4
   �   x�U���0����m"�L!�4ă!M����&s�ק}z���J����>��]����'*m\D��V��dCi�S��6���I��E"ZV:�-a��ӈ�n@�G�ˑ�s�C`)������	��7�'�~4ת)˟���ۿu�����4�      5
   �   x���v
Q���W(I�/H,,M-IUs�	uV�P7 Cuu�b��<�<�TH��+�L�KU0U�M,N�J-��/Nю�%�e�@�_����5�'��8�4b|Ui����X!'3� 3�*�X!-193lSXjQJ*6+�� �KbA      6
   n  x���]k�0����s�!M���̩B��u����DN����פ26�.�E9y{��9'���f���|�/Q���ڵ�� zg���q���px��������B��Mj��/��:}���a�7LY�8v�-�G(3M@[��X��?�}�j.ը0����q��wg��fY�����V	F	qF���E����+ e)H�]�L�dŕS��5Γ�WA'���N%���/���!j�ϣ�n���C�7��U�i�H��yV\KԷ@؋�8d1�1;A��
^���y5�_eR0�!�b���݌�ƛZ�=3,��(�������$N�����Q)��: �4𦥩�E?|�)      7
   p   x���v
Q���W(I�/(JMK-J�K�LTs�	uV�P7 Cuu�����D���ĲT�1�$�,Ă)���Ѵ��$�T#�ްԜ�b�g6%�4Q�$3)3��\\ F_E+      8
   �   x���v
Q���W(I�/(JMK-J�K�L�O��L�+IUs�	uV�P7 CuuCKCC#"�i��I�IFF���F�0���7������ �&crL2��&�MBs�)9&cq3�MB��9�$.. ����      9
   �   x���v
Q���W(I�/(JMK-J�K�L�/N-*�L��Ws�	uV�P7 CuL�_����5�'٦��F�0��0��i�0�	L34��fJ�iF�`F�̑�7
L3��%5L3G��i\\ y�i      :
   
   x���          ;
   
   x���          <
   
   x���          =
   �   x���?�0��=��(8h��8u�(Ulu��h�����C�$��8xxMU�L� ܮ�-
��.yy.j���t+� }d�����?;w����{e�S��8�ɧ)�`�,x
(d���7Iӂ�����ӿKrv�	�a��S��HI~�      >
   �   x���v
Q���W(I�/J-.(M-.ITs�	uV�P7 Cu��L�\�Y���i��I�V#�&�|r��4�fV%����ɵ�$�R!%U!1�4�(�,w�B���J��@�|S�K�R�`uL.V3�� BX�      ?
   F   x���v
Q���W(I�/J-.(M-.I�O��M�+I,��Ws�	uV�P7 Cu�PdRӚ�� �^      @
   
   x���          A
   �   x���v
Q���W(I�/��Qs�	uV�Pw-.I-�L�,.IT�QP7 C�1�$�,_]Ӛ��n�ԢԼ�T�N#�u�&�$g��!�lL�~�Ld�M��	⤤�����5%^�Ki���0�f��� �]f�      B
     x��S=O�0��+�$�|��T�H� ��+:�J|V�d��cW�H�%w�����\�M�Ҋ�n��}{58-$��~[�F\ĉ[i|%�FM��I0=q?�����c��WI��6��wU�z[ii�c}y���<Zˊz��,��dge�=�=j�&VB���IS~V��/6C!�ևG�Ź#��,:��l�M;���`���9	�냕�� Q�M o<��Do��q��r2�-�w����غth
���ģ50���G�7�#h      C
   
   x���          D
   G   x���v
Q���W(I�/�,.I�MTs�	uV�P7 Cuu��̲|�)17�����Ѵ��� �|      E
   S   x���v
Q���W(I�/��LI-Rs�	uV�P7 Cu�`�����pL.�,�GH)��*��&%�� �4���� o��      F
   i   x���v
Q���W(I�/���L�,)MQs�	uV�P7 CuuCKCC#�1�$�,�2204�50��Ѵ��$�8#�q`��8#�3F2��q� 㸸 GO;�      G
   Y   x���v
Q���W(I�/�,ȏOL.M-J�Ws�	uV�P7 Cuu����Լ�|�1�$�,_]Ӛ˓X3���r2�Q��� 4�(      H
   ^   x���v
Q���W(I�/�,ȏO��L�+IUs�	uV�P7 Cu����Լ����1�$�,_]Ӛ˓XC�@��R�3�K�S�QM�� �%)�      I
   �   x���v
Q���W(I�/�,ȏO��M�+I,��Ws�	uV�P7 Suu���ļ̪����<�1�$�,_]Ӛ˓�L@Z]�KRK2�3�K�7���3hF�n2iN-*:)�|c� ��q�      J
   �   x���v
Q���W(I�/�,ȏO�K.M-.ITs�	uV�P7 Cu��Ԣ����|�1�$�,_]Ӛ˓hC�@=��9�@c��5��ѵ�$���,�昀4����C�� ���E�E�b�螚�J�� ���)��KTp� ��Fqq ħ��      K
   q   x���v
Q���W(I�/�,ȏ��K�LI�
a�>���
�@`����Z���f&�d��kZsy�`�	H�IQ~1�f�����&��Q�c�V�1%��h�pq �KTL      L
   |   x���v
Q���W(I�/�,ȏ��/�L�LTs�	uV�P7 #u���������<�1�$�,_]Ӛ˓XCA�\�R�JJs2SS�4���3/-�(7�9�LcL@��S�R�sP��� �S�      M
   �   x���v
Q���W(I�/�,ȏ��/�L�LNL���Ss�	uV�P7 Cuu�ԼԢ��1�$�,_]Ӛ˓$s�@�R����s2�S)1���9?/-�("�YB��& �~@��L��1k.K�KNE5�� s%s      N
   q   x���v
Q���W(I�/�,ȏ�/JO�ˬJL���Ss�	uV�P7 Cu��Ԣ�L�\1��\�Y���i��I�IF ;�y�i��%�E���e�윟�Z�����j nE;      O
   N   x���v
Q���W(I�/�,ȏ/(JMK-J�K�LTs�	uV�P7 Cu��� �0�1�$�,_]Ӛ�� ��Q      P
   �   x���=�0��=�"[�vp*%J@+��Z�4P�����ލnoYއ�T��檪/�\ML���`��8ݤ�1�Y�ڦ�u.п���AL�L��,)T~ �d|���P`�A-�YSx�UL�Kl(,C�!�#[
���?��g���x����c)��^      Q
   p   x���v
Q���W(I�/�,ȏ/JM�/�O�L�Qs�	uV�P7 Cuu���Ԥ��l�1�$�,����Ѵ��$�0#��y�%��E����f2 �<��$��Y\\ DWD�      R
   w   x���v
Q���W(I�/�,ȏ/N-*�L��Ws�	uV�P7 Cuu��������1�$�,����Ѵ��$�(#�v���LJ2iwO�K��I& ���)@��D� ��@.. �TY�      S
   t  x���OK�0����UAF��o��;d��^%�b��&#m����
���\~y�_�X����l�\?e��k:��^g�/�Uv�+��{�je��u~��X'@ȸGâ�fV�������?��{P��M�3� ��FMd��w�v��M�H�K�V��V�k($�����n�r��sE�""1je�3�A78RD2���$���W�Y.0 O��/wJ��é������T �L���A�GH!$P���!<.H�y���)���ŉIC��v��ٛ"� �Îg�O[_�h���p����ʨ�X��=�%w��ڎ.�K�����cS����������#*ʸ8��]GU���k"	1�ȥ�M&_Z�թ      T
   �   x���v
Q���W(I�/-.M,�̏/OMRs�	uV�P7�440142Q�QPwJ-*���W �݊�S��32�2A��4$#c��������\�$�KS3#ss �/��GӚ˓ۍ�-L�,����$B�
�/*J-��AB%`�!+M�M�M�-Vrq �F;     