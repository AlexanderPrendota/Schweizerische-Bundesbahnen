CREATE TABLE public.attendance
(
  attendance_id bigint NOT NULL,
  count integer,
  date date,
  CONSTRAINT attendance_pkey PRIMARY KEY (attendance_id),
  CONSTRAINT uk_3efju6boye81nlvyplplv9voo UNIQUE (date)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.attendance
  OWNER TO postgres;

CREATE TABLE public.chats
(
  chat_id bigint NOT NULL,
  CONSTRAINT chats_pkey PRIMARY KEY (chat_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.chats
  OWNER TO postgres;


CREATE TABLE public.messages
(
  message_id bigint NOT NULL,
  text character varying(255) NOT NULL,
  time_stamp timestamp without time zone NOT NULL,
  chat_id bigint,
  sender_id bigint,
  CONSTRAINT messages_pkey PRIMARY KEY (message_id),
  CONSTRAINT fk4ui4nnwntodh6wjvck53dbk9m FOREIGN KEY (sender_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk64w44ngcpqp99ptcb9werdfmb FOREIGN KEY (chat_id)
      REFERENCES public.chats (chat_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.messages
  OWNER TO postgres;


CREATE TABLE public.rides
(
  id bigint NOT NULL,
  time_arrival timestamp without time zone,
  time_departure timestamp without time zone,
  seat_id bigint,
  station_arrival_id bigint,
  station_departure_id bigint,
  ticket_id bigint,
  train_id character varying(255),
  CONSTRAINT rides_pkey PRIMARY KEY (id),
  CONSTRAINT fk3ji3fdsu45km8fk5bk0id94c2 FOREIGN KEY (ticket_id)
      REFERENCES public.tickets (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk6tm3tt7m8h132gq7tqcfi9vax FOREIGN KEY (station_arrival_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkgakpjw7wsyxeruw05ibcnrmsa FOREIGN KEY (train_id)
      REFERENCES public.trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkhg9aofdnfyc0ru4m1lt67mtbs FOREIGN KEY (station_departure_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkkbv04ruoe7absbqm1ol29c07 FOREIGN KEY (seat_id)
      REFERENCES public.seats (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.rides
  OWNER TO postgres;


CREATE TABLE public.roles
(
  id bigint NOT NULL,
  role character varying(255),
  CONSTRAINT roles_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.roles
  OWNER TO postgres;

CREATE TABLE public.schedules
(
  id bigint NOT NULL,
  time_arrival timestamp without time zone,
  time_departure timestamp without time zone,
  station_arrival_id bigint,
  station_departure_id bigint,
  train_id character varying(255),
  CONSTRAINT schedules_pkey PRIMARY KEY (id),
  CONSTRAINT fk55b3ky0qoekyyjxiwh7e1o32x FOREIGN KEY (train_id)
      REFERENCES public.trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkem0cgu2o4chrt8b49ores48rl FOREIGN KEY (station_departure_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkobt5o0ej5oe75s4pspy6wfivt FOREIGN KEY (station_arrival_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.schedules
  OWNER TO postgres;

CREATE TABLE public.seats
(
  id bigint NOT NULL,
  cabine integer,
  "number" integer,
  train_id character varying(255),
  CONSTRAINT seats_pkey PRIMARY KEY (id),
  CONSTRAINT fkthnax36uccd771sdt7fped2p1 FOREIGN KEY (train_id)
      REFERENCES public.trains (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.seats
  OWNER TO postgres;

CREATE TABLE public.stations
(
  id bigint NOT NULL,
  station_name character varying(255),
  x integer,
  y integer,
  CONSTRAINT stations_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.stations
  OWNER TO postgres;

CREATE TABLE public.tickets
(
  id bigint NOT NULL,
  user_id bigint,
  CONSTRAINT tickets_pkey PRIMARY KEY (id),
  CONSTRAINT fk4eqsebpimnjen0q46ja6fl2hl FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.tickets
  OWNER TO postgres;



CREATE TABLE public.trains
(
  id character varying(255) NOT NULL,
  CONSTRAINT trains_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.trains
  OWNER TO postgres;


CREATE TABLE public.user_chats
(
  user_chat_id bigint NOT NULL,
  chat_id bigint,
  user_id bigint,
  CONSTRAINT user_chats_pkey PRIMARY KEY (user_chat_id),
  CONSTRAINT fk7wnur32eifid4w1671b9gnrny FOREIGN KEY (chat_id)
      REFERENCES public.chats (chat_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fktdc8rg12fl9yhkd7cqjc2j0oj FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_chats
  OWNER TO postgres;

CREATE TABLE public.user_roles
(
  user_id bigint,
  role_id bigint,
  CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.users
(
  id bigint NOT NULL,
  birthday date,
  email character varying(255),
  firstname character varying(255),
  image_path character varying(255),
  lastname character varying(255),
  password character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;
