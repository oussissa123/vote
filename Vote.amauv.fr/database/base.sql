PGDMP     4    4                y            vote    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    657857    vote    DATABASE     �   CREATE DATABASE vote WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE vote;
                postgres    false            �            1259    657858 
   candidates    TABLE     �   CREATE TABLE public.candidates (
    matricule character varying(50) NOT NULL,
    nom character varying(25),
    prenom character varying(25),
    vote integer,
    img character varying(50)
);
    DROP TABLE public.candidates;
       public         heap    postgres    false            �            1259    657861    votants    TABLE     �   CREATE TABLE public.votants (
    code character varying(100) NOT NULL,
    nom character varying(25),
    prenom character varying(25),
    telephone character varying(12) NOT NULL
);
    DROP TABLE public.votants;
       public         heap    postgres    false            �            1259    657864    votes    TABLE     �   CREATE TABLE public.votes (
    code character varying(50) NOT NULL,
    nom character varying(25),
    prenom character varying(25),
    telephone character varying(12) NOT NULL
);
    DROP TABLE public.votes;
       public         heap    postgres    false                      0    657858 
   candidates 
   TABLE DATA           G   COPY public.candidates (matricule, nom, prenom, vote, img) FROM stdin;
    public          postgres    false    202   �                 0    657861    votants 
   TABLE DATA           ?   COPY public.votants (code, nom, prenom, telephone) FROM stdin;
    public          postgres    false    203   T                 0    657864    votes 
   TABLE DATA           =   COPY public.votes (code, nom, prenom, telephone) FROM stdin;
    public          postgres    false    204          �
           2606    657870    candidates candidates_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.candidates
    ADD CONSTRAINT candidates_pkey PRIMARY KEY (matricule);
 D   ALTER TABLE ONLY public.candidates DROP CONSTRAINT candidates_pkey;
       public            postgres    false    202            �
           2606    657868    votants votants_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.votants
    ADD CONSTRAINT votants_pkey PRIMARY KEY (code);
 >   ALTER TABLE ONLY public.votants DROP CONSTRAINT votants_pkey;
       public            postgres    false    203            �
           2606    657877    votants votants_telephone_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.votants
    ADD CONSTRAINT votants_telephone_key UNIQUE (telephone);
 G   ALTER TABLE ONLY public.votants DROP CONSTRAINT votants_telephone_key;
       public            postgres    false    203            �
           2606    657872    votes votes_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.votes
    ADD CONSTRAINT votes_pkey PRIMARY KEY (code);
 :   ALTER TABLE ONLY public.votes DROP CONSTRAINT votes_pkey;
       public            postgres    false    204            �
           2606    657881    votes votes_telephone_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.votes
    ADD CONSTRAINT votes_telephone_key UNIQUE (telephone);
 C   ALTER TABLE ONLY public.votes DROP CONSTRAINT votes_telephone_key;
       public            postgres    false    204               m   x��14��N��/-9���%+5'371%�ӈ3'5/3/U/� 5�+��9�4'3)1��38�4753'��Ȓ�ʆ�s��/-�_����X�ilə�X\�Z	Q���� &�&�         �  x�]T�nQ���@r��Ϋ3QD�HI��+�Wr�|=3�8{m�Ҝy�9g�f�r{����L���x{�S^��ĈԶ7;��xNߏ��q8����t�6�"ed������Ӱ9L��Uc�����^�x�����j�#]�7_���E@C����л��c�.?��憈�0�9*E\{��L�� -�_ 'r����W1����)�5��Q�_�`�zY��X�*��	�t�VPts6m�a��5]Z�C��=�C
ު,�B�"0E#�n�:�����쪂@��G��wy{F�.�P�hi�DB�� 	� 7֗�K�e7m٪,�T͹�F�`'���B�+Q,�)`'/c��9ׄzi���۪���I #*�w��I��deg}�T� Q���:
@�"���?��X� k@\�&�3[Q5���CW!������n���ݯ�'��_�u�Q���a)�lԯ�?����y�Q́�Tؕ��	�i�A�|K�˰�˟��q�SS"En�*ɍ�NR�9��O���J��X�&eҌ�V/�]|��qs����=3M1r�"ai �W�k��(]α}'�q�ٮ������#�ײh����=v'���x<�j������إ����[��˫�����6Ϋ*>�u4���|	���K�3-�v��y�=���45�Z�6Q�S��������n���n�� �e�         G  x�mUAn[G]]CK�Ő�!9;)0i �&@6ߖ�8��B�>BoҞ���H��ST-�!���#u�T�~����0$�%UJ)-���S��Y�Z��,��R�9�Ʌ/�W:�]kF9E�U�L�j����i(�=sMh��Bo�rE������o�y���+E��͛�Qa�X4�x���;���`�եӻ���nw���=jKf7/9*�\j���FpR��D�:a���71��AɎd�nxA�f��ן�Ǉa���a�d5k��Ӄ6h�Ќ��4���)��:�V�-MI���윛��r8S�t5t;�֏������ 괮U���?}ݏ���?ç�i��аz�����-͚�"`��J��Bv�,W�0&�-u$2Yi�] �Y�M�o�����%����{��W@�*�����%\ `j* G�&���4�KJ��W�[b�l�({J��a��ߌpHsP26hJ��3(b�9hE�M-�[rf0�[�XuJ��4}�3�~{�>O�shĮ%��/���6��"�Gx�s��L��r�� �S��wUBc�N|܎�S��mZ�=�=O�y�%�ʒ�BDc�QtzOť�Iێ8�,��?�N��ږ�x:�7?w��8!�ݱ��t�[r˕�ɲPw�~eܩ0K�i���MCT�0�p1V0p���������� ��_=�{ZC�3
Dŭ�&���J�̩���A~���?q\*i���4�La�-Og�f���X�����9�~5[���f�?EsUj{�)�S��J�Rʽc`\��B�}���Uk��!���Rz������׿������߭!�S�l���]�q�[,�+���     