ALTER TABLE public.tattoo_artists
ADD COLUMN sum_of_ratings INTEGER DEFAULT 0 ,
ADD COLUMN total_ratings DOUBLE PRECISION DEFAULT 0.0 ;
