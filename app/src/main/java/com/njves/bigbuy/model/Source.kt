package com.njves.bigbuy.model

import java.util.*
// Содержит информацию о источнике дохода/расхода
// id - уникальный идентификатор для базы данных
// title - название истоника
data class Source(val id: UUID, val title: String)
