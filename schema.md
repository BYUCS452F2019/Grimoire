# Grimoire Schema

## user

This table represents users that have registered to use the app. As
such, it is named `user`.

#### Schema

user(user_name, password)

> Primary Key `user_name`

#### Relationships

The user table contains a `user_name` column that is used as the primary
key. The `user_spell_books` table, described below, contains a foreign
key that maps to this column.

#### Evidence of Normalization

The relationship described above is evidence of normalization in this
table.

#### Column Names

`user_name` - The username for a user.

`password` - the password for a user.

## user_spell_books

This table links users with spell books they have access to. As such,
it is named `user_spell_books`.

#### Schema

user_spell_books(book_id, user_name)

> Foreign Key `book_id`
>
> Foreign Key `user_name`

#### Relationships

This table contains 2 foreign keys that link users with their spell
books.

#### Evidence of Normalization

The relationship described above is evidence of normalization in this
table.

#### Column Names

`book_id` - The book id a user has access to.

`user_name` - The user who has access to the corresponding `book_id`.

## spell_book

This table tracks the spells present in a spell book. As such, it is
named `spell_book`.

#### Schema

spell_book(book_id, spell_id)

> Primary Key `book_id`
>
> Foreign Key `spell_id`

#### Relationships

This table contains keys (primary and foreign) that describe which
spells are present in which spellbooks.

#### Evidence of Normalization

The relationship described above is evidence of normalization in this
table.

#### Column Names

`book_id` - The book to which a spell belongs.

`spell_id` - The spell present in the book.

## spells

This table describes spells. As such, it is named `spells`.

#### Schema

spell(spell_id, spell_name, level, ritual, casting_time, range, verbal, somatic, material, school, duration, concentration, target, saving_throw, description, higher_levels, damage, damage_type, class, book)

> Primary Key `spell_id`

#### Relationships

This table has a primary key identifying the spell. This primary key is
referenced in the `spell_book` table.

#### Evidence of Normalization

The relationship described above is evidence of normalization in this
table.

#### Column Names

`spell_id` - The spell id

Other columns in this table come from the Dungeons and Dragons Player's Handbook section on spells.

## Additional Information

Additional information can be found in the pdf [here](Grimoire%20DB.pdf)
