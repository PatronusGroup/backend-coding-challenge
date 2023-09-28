package com.patronusgroup.livecodingchallenge.persistence.dao

import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "holder")
class HolderDao(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val uuid: UUID,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "address")
    val address: String,
    @Column(name = "birthday")
    val birthday: Date
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HolderDao

        if (uuid != other.uuid) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (address != other.address) return false
        if (birthday != other.birthday) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + birthday.hashCode()
        return result
    }

    fun toDomain(): Holder {
        return Holder(
            uuid = uuid,
            firstName = firstName,
            lastName = lastName,
            address = address,
            birthday = birthday
        )
    }
}
