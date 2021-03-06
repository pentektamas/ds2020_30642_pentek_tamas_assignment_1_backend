PGDMP                     
    x            city-db    13.0    13.0 &    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16394    city-db    DATABASE     h   CREATE DATABASE "city-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Romanian_Romania.1250';
    DROP DATABASE "city-db";
                postgres    false            �            1259    41388    account    TABLE     �   CREATE TABLE public.account (
    id bytea NOT NULL,
    account_type integer NOT NULL,
    password character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    41396 	   caregiver    TABLE     �   CREATE TABLE public.caregiver (
    id bytea NOT NULL,
    address character varying(255) NOT NULL,
    birthdate date NOT NULL,
    gender character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    account_id bytea
);
    DROP TABLE public.caregiver;
       public         heap    postgres    false            �            1259    41404    caregiver_patients    TABLE     l   CREATE TABLE public.caregiver_patients (
    caregiver_id bytea NOT NULL,
    patients_id bytea NOT NULL
);
 &   DROP TABLE public.caregiver_patients;
       public         heap    postgres    false            �            1259    41410    doctor    TABLE     L   CREATE TABLE public.doctor (
    id bytea NOT NULL,
    account_id bytea
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    41418 
   medication    TABLE     �   CREATE TABLE public.medication (
    id bytea NOT NULL,
    dosage character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    side_effects character varying(255) NOT NULL
);
    DROP TABLE public.medication;
       public         heap    postgres    false            �            1259    41426    medication_plan    TABLE     f   CREATE TABLE public.medication_plan (
    id bytea NOT NULL,
    treatment_period integer NOT NULL
);
 #   DROP TABLE public.medication_plan;
       public         heap    postgres    false            �            1259    41434    medication_plan_medications    TABLE     ~   CREATE TABLE public.medication_plan_medications (
    medication_plan_id bytea NOT NULL,
    medications_id bytea NOT NULL
);
 /   DROP TABLE public.medication_plan_medications;
       public         heap    postgres    false            �            1259    41440    patient    TABLE     ?  CREATE TABLE public.patient (
    id bytea NOT NULL,
    address character varying(255) NOT NULL,
    birthdate date NOT NULL,
    gender character varying(255) NOT NULL,
    medical_record character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    account_id bytea,
    medication_plan_id bytea
);
    DROP TABLE public.patient;
       public         heap    postgres    false            �            1259    41030    person    TABLE     �   CREATE TABLE public.person (
    id bytea NOT NULL,
    address character varying(255) NOT NULL,
    age integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.person;
       public         heap    postgres    false            �          0    41388    account 
   TABLE DATA           H   COPY public.account (id, account_type, password, user_name) FROM stdin;
    public          postgres    false    201   .       �          0    41396 	   caregiver 
   TABLE DATA           U   COPY public.caregiver (id, address, birthdate, gender, name, account_id) FROM stdin;
    public          postgres    false    202   0/       �          0    41404    caregiver_patients 
   TABLE DATA           G   COPY public.caregiver_patients (caregiver_id, patients_id) FROM stdin;
    public          postgres    false    203   0       �          0    41410    doctor 
   TABLE DATA           0   COPY public.doctor (id, account_id) FROM stdin;
    public          postgres    false    204   �0       �          0    41418 
   medication 
   TABLE DATA           D   COPY public.medication (id, dosage, name, side_effects) FROM stdin;
    public          postgres    false    205   �0       �          0    41426    medication_plan 
   TABLE DATA           ?   COPY public.medication_plan (id, treatment_period) FROM stdin;
    public          postgres    false    206   �1       �          0    41434    medication_plan_medications 
   TABLE DATA           Y   COPY public.medication_plan_medications (medication_plan_id, medications_id) FROM stdin;
    public          postgres    false    207   �2       �          0    41440    patient 
   TABLE DATA           w   COPY public.patient (id, address, birthdate, gender, medical_record, name, account_id, medication_plan_id) FROM stdin;
    public          postgres    false    208   V3       �          0    41030    person 
   TABLE DATA           8   COPY public.person (id, address, age, name) FROM stdin;
    public          postgres    false    200   �5       L           2606    41395    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    201            N           2606    41403    caregiver caregiver_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.caregiver
    ADD CONSTRAINT caregiver_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.caregiver DROP CONSTRAINT caregiver_pkey;
       public            postgres    false    202            R           2606    41417    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    204            T           2606    41425    medication medication_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.medication DROP CONSTRAINT medication_pkey;
       public            postgres    false    205            V           2606    41433 $   medication_plan medication_plan_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.medication_plan
    ADD CONSTRAINT medication_plan_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.medication_plan DROP CONSTRAINT medication_plan_pkey;
       public            postgres    false    206            X           2606    41447    patient patient_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.patient DROP CONSTRAINT patient_pkey;
       public            postgres    false    208            J           2606    41037    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    200            P           2606    41449 /   caregiver_patients uk_1nneutl39854dl476m6t72dqr 
   CONSTRAINT     q   ALTER TABLE ONLY public.caregiver_patients
    ADD CONSTRAINT uk_1nneutl39854dl476m6t72dqr UNIQUE (patients_id);
 Y   ALTER TABLE ONLY public.caregiver_patients DROP CONSTRAINT uk_1nneutl39854dl476m6t72dqr;
       public            postgres    false    203            ^           2606    41475 7   medication_plan_medications fk3ijvp1kbhl9o1sexh7coev4lo    FK CONSTRAINT     �   ALTER TABLE ONLY public.medication_plan_medications
    ADD CONSTRAINT fk3ijvp1kbhl9o1sexh7coev4lo FOREIGN KEY (medication_plan_id) REFERENCES public.medication_plan(id);
 a   ALTER TABLE ONLY public.medication_plan_medications DROP CONSTRAINT fk3ijvp1kbhl9o1sexh7coev4lo;
       public          postgres    false    206    207    2902            \           2606    41465 "   doctor fk464ali1gjqfqsmov2usdt6onm    FK CONSTRAINT     �   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT fk464ali1gjqfqsmov2usdt6onm FOREIGN KEY (account_id) REFERENCES public.account(id);
 L   ALTER TABLE ONLY public.doctor DROP CONSTRAINT fk464ali1gjqfqsmov2usdt6onm;
       public          postgres    false    201    204    2892            `           2606    41485 #   patient fk5hur4f1tiy2msg4shsm30dtwl    FK CONSTRAINT     �   ALTER TABLE ONLY public.patient
    ADD CONSTRAINT fk5hur4f1tiy2msg4shsm30dtwl FOREIGN KEY (medication_plan_id) REFERENCES public.medication_plan(id);
 M   ALTER TABLE ONLY public.patient DROP CONSTRAINT fk5hur4f1tiy2msg4shsm30dtwl;
       public          postgres    false    208    2902    206            Z           2606    41455 .   caregiver_patients fk7q54wdg7dd4fty4p6nfeytrae    FK CONSTRAINT     �   ALTER TABLE ONLY public.caregiver_patients
    ADD CONSTRAINT fk7q54wdg7dd4fty4p6nfeytrae FOREIGN KEY (patients_id) REFERENCES public.patient(id);
 X   ALTER TABLE ONLY public.caregiver_patients DROP CONSTRAINT fk7q54wdg7dd4fty4p6nfeytrae;
       public          postgres    false    203    208    2904            ]           2606    41470 7   medication_plan_medications fkb6n23pjm86q0gs52fn4st5e8s    FK CONSTRAINT     �   ALTER TABLE ONLY public.medication_plan_medications
    ADD CONSTRAINT fkb6n23pjm86q0gs52fn4st5e8s FOREIGN KEY (medications_id) REFERENCES public.medication(id);
 a   ALTER TABLE ONLY public.medication_plan_medications DROP CONSTRAINT fkb6n23pjm86q0gs52fn4st5e8s;
       public          postgres    false    205    207    2900            [           2606    41460 .   caregiver_patients fkhb9jvk6yfv2njkitk8yqwwjb4    FK CONSTRAINT     �   ALTER TABLE ONLY public.caregiver_patients
    ADD CONSTRAINT fkhb9jvk6yfv2njkitk8yqwwjb4 FOREIGN KEY (caregiver_id) REFERENCES public.caregiver(id);
 X   ALTER TABLE ONLY public.caregiver_patients DROP CONSTRAINT fkhb9jvk6yfv2njkitk8yqwwjb4;
       public          postgres    false    2894    203    202            _           2606    41480 #   patient fkn6atqtmaryxq3o213gi3or1ij    FK CONSTRAINT     �   ALTER TABLE ONLY public.patient
    ADD CONSTRAINT fkn6atqtmaryxq3o213gi3or1ij FOREIGN KEY (account_id) REFERENCES public.account(id);
 M   ALTER TABLE ONLY public.patient DROP CONSTRAINT fkn6atqtmaryxq3o213gi3or1ij;
       public          postgres    false    2892    208    201            Y           2606    41450 %   caregiver fkpbyfbsaxgdmwbfyfw03wnvqd6    FK CONSTRAINT     �   ALTER TABLE ONLY public.caregiver
    ADD CONSTRAINT fkpbyfbsaxgdmwbfyfw03wnvqd6 FOREIGN KEY (account_id) REFERENCES public.account(id);
 O   ALTER TABLE ONLY public.caregiver DROP CONSTRAINT fkpbyfbsaxgdmwbfyfw03wnvqd6;
       public          postgres    false    202    201    2892            �     x�e�;n�0��>LAQ|Hw�Td)� (r���Tw��$ǓX�����T�x'PS�ҩne+Xi��~���&*R�aDՠ3���������G9d7��$ʭk�NY[�5���Дn��]����,.P� ˼��2]Y��h*��K��&�͵����i�Z�hH�tj(6�GøN���ӬS4�c&�|[�ֺ�����#/�U9�s�ĵ�:N��&�>>o?��J.b}ĳ�M�J/ ���D�,����c��_4�~�      �   �   x�]�AN1�ur�9 ANbǓu%��d�8TTT4���3���������� �"�y�B %.�QIe)�h���u5�N�O��|{G����X�!&w������z��u�_̵vȉ:3�&2R���Fg�u2� ]���A1���XR2J�<�p	PC�b�	YO�@���g��&nQK�P�*è�}{��� ��L�      �   �   x��̱C1 �:&���0̐��A����IT�v�Kr�`����ޯ�<�R¢��4T]�����q�s����]�m�[Z�2��%�&@dEy%$������]Һ�FhA���`���7�<u      �   6   x����01577�43J5K371L3K�4107JM33NK4�41�!�Ƙ+F��� �O      �   �   x�m�1N�0���9EN���{\�Q�V�M3��!RB$i��%�k�_o�r)��deRN%3ŜL��*����緵ʼL��ۤ�[kV���G�CBh%���,�}h�Pj���M>��.�:�W�"�1S�(����'�Ȏ����i�݋�J}�
������)��y�8h�����.�ݎq���_���*�Oo      �   �   x�%ϻq 1E�x�$��M��\��Q~�>��_tK��B:x�mlgs������TM��&7����&E�^|j�U��W�� 0Q�W�ZdAt ���<��,pU�Y#(\�"q�mV!6��"����[�6�ZG�.�VW�y�.*�f���4�!����{>H��E�)RL�ik���?�� Ȱ�'���kϪ���������]u      �   �   x��й�1DA[�H�8rg
��X�}�7����:���&'�g������R#�̭�J%L�M��H�u?s ̿��D�b۵W�>5��OS`��m���j�����sdLܢP�p��4f����O�Ȇ	b�.u���;z{"�����Q �-R��;?U����u]��yX      �   =  x�e�ͪ�7���U��2�F��R
�"�z3?R��.�CN�>�\ZNNڈ��;�N����7�Љ����!#,����z��~�~(����󗿎��kخ��ڱ��;�������o�?�O�����P{ДY���0�f��W�^l�b�C�Kb9fV -,��b�2���2�NE�����u[��+��Ho��@��w�������l�tXg,�۷�"�O�H�E��Ƣi"�m˄G���@M=d��9zf�P5z�	�ATk>k- �>[�A�Qߢ
�k���]b�v����.y���;E���HNҚM��e�[fB+�i-^�ɣtՁ��m=����ȌH�yM�b�S�6���5��T��p�����߮�?��e�.�yCkň��HQ*�L	�ץClS�"�Rd���Č5��͂k�s]���b֖��"&��n�g�Y�O��c�o��t�q�?xr��̮زJ.�Kز���OeS϶�lS�s%�����If]�R��>���sh���[J����?e>�ܾ��a'NRfuLY"�U��2�@��2͝l)�*��A���ԑ�k�O����"_      �      x������ � �     