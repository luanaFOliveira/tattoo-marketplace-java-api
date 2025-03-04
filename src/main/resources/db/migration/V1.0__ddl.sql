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

CREATE TABLE IF NOT EXISTS public.images (
    id BIGSERIAL PRIMARY KEY,
    key VARCHAR(255) NOT NULL,
    url VARCHAR(255),
    entity_id BIGINT NOT NULL, 
    entity_type VARCHAR(50) NOT NULL CHECK (entity_type IN ('Quote', 'TattooArtist')),

    CONSTRAINT fk_entity_quote FOREIGN KEY (entity_id) REFERENCES public.quotes(id) ON DELETE CASCADE,
    CONSTRAINT fk_entity_tattoo_artist FOREIGN KEY (entity_id) REFERENCES public.tattoo_artists(id) ON DELETE CASCADE
);




