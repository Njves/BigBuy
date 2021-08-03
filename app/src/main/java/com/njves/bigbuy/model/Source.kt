package com.njves.bigbuy.model

import java.util.*
// Содержит информацию о источнике дохода/расхода
// id - уникальный идентификатор для базы данных
// title - название истоника
class Source(val title: String) {
    private var id = UUID.randomUUID()
    constructor(id: UUID, title: String) : this(title) {
        this.id = id
    }
}
