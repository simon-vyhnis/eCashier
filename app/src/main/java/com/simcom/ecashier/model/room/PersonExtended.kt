package com.simcom.ecashier.model.room

import androidx.room.Embedded

class PersonExtended(@field:Embedded val person: Person, val hasPaid: Boolean)