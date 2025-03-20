CREATE TABLE IF NOT EXISTS public.users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    location VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tattoo_artists (
    id BIGINT PRIMARY KEY REFERENCES public.users(id) ON DELETE CASCADE,
    rate INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS public.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS public.artist_categories (
    artist_id INT NOT NULL REFERENCES public.tattoo_artists(id) ON DELETE CASCADE,
    category_id INT NOT NULL REFERENCES public.categories(id) ON DELETE CASCADE,
    PRIMARY KEY (artist_id, category_id)
);

CREATE TABLE IF NOT EXISTS public.status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS public.quotes (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    placement VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    size NUMERIC(10,2) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL REFERENCES public.users(id) ON DELETE CASCADE,
    tattoo_artist_id BIGINT NOT NULL REFERENCES public.tattoo_artists(id) ON DELETE SET NULL,
    status_id BIGINT NOT NULL REFERENCES public.status(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.tattoo_artist_images (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255),
    tattoo_artist_id BIGINT NOT NULL REFERENCES public.tattoo_artists(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.quote_images (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(255),
    quote_id BIGINT NOT NULL REFERENCES public.quotes(id) ON DELETE CASCADE
);



