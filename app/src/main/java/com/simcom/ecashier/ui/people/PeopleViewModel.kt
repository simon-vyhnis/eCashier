package com.simcom.ecashier.ui.people

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.simcom.ecashier.model.room.CashierDatabase
import com.simcom.ecashier.model.room.Group
import com.simcom.ecashier.model.room.Person
import com.simcom.ecashier.model.room.PersonToGroup
import kotlinx.coroutines.launch

class PeopleViewModel(application:Application) : AndroidViewModel(application) {
    private var db: CashierDatabase = Room.databaseBuilder(
        application,
        CashierDatabase::class.java, "cashier_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    var selectedGroup: Group? = null
    var selectedPerson: Person? = null

    fun getGroups() : LiveData<List<Group>>{
        return db.groupDao().allGroups()
    }

    fun getPeople() : LiveData<List<Person>>? {
        return selectedGroup?.let { db.personToGroupDao().getPeopleFromGroup(it.id) }
    }

    fun addGroup(name: String) = viewModelScope.launch {
        db.groupDao().insert(Group(name))
    }
    fun addPerson(name: String) = viewModelScope.launch {
        selectedGroup?.let {
            val personId = db.personDao().insertPerson(Person(name))
            db.personToGroupDao().insert(PersonToGroup(personId, it.id))
        }
    }

    fun deletePerson(person: Person) = viewModelScope.launch{
        selectedGroup?.let{db.personToGroupDao().deleteWithIds(person.id, it.id)}
    }

    fun deleteGroup(group: Group) = viewModelScope.launch{
            db.groupDao().delete(group)
            db.personToGroupDao().deleteWithGroupId(group.id)
    }

    fun editPerson(personName: String) =  viewModelScope.launch{
        selectedPerson?.let {
            it.name = personName
            db.personDao().editPerson(it)
        }
    }
    fun editGroup(groupName: String) =  viewModelScope.launch{
        selectedGroup?.let {
            it.name = groupName
            db.groupDao().update(it)
        }
    }

}