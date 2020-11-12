

INSERT INTO public.role(id_role, nombre_role)
	VALUES (1,'ROLE_ESTUDIANTE');
	
INSERT INTO public.role(id_role, nombre_role)
	VALUES (2,'ROLE_ADMIN');

INSERT INTO public.role(id_role, nombre_role)
	VALUES (3,'ROLE_EMPRESARIO');

INSERT INTO public.users(
	 adress_user, contra, date_user, email_user, gender_user, institution_user, name_user, occupation_user, phone_user, id_role)
	VALUES ('Mz R','$2a$10$RpqKEzRnQ0iP4ksZn9BnUOYk170hxinbBJQeW7V13Tk5AW9fWv/0C', '2017-10-15', 'hola@gmail.com', 'femenino', 'UPC', 'hola', 'estudiante', '4421210', 1);

INSERT INTO public.users(
			adress_user, contra, date_user, email_user, gender_user, institution_user, name_user, occupation_user, phone_user, id_role)
	VALUES ('a','$2a$10$.XoPxl163slSc9l4LgWn3O2aSvAn/22iSj.XqewKaGxJXnhTL598G', '2020-10-15', 'admin@gmail.com', 'masc', 'UPC', 'admin', 'estudiante', '999999999', 2);

