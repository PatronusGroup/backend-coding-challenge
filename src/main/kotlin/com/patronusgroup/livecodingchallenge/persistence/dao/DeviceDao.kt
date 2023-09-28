package com.patronusgroup.livecodingchallenge.persistence.dao

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
@Table(name = "device")
class DeviceDao(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    val uuid: UUID,
    @Column(name = "serial_number")
    val serialNumber: String,
    @Column(name = "phone_number")
    val phoneNumber: String,
    @Column(name = "model")
    val model: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeviceDao

        if (uuid != other.uuid) return false
        if (serialNumber != other.serialNumber) return false
        if (phoneNumber != other.phoneNumber) return false
        if (model != other.model) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + serialNumber.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + model.hashCode()
        return result
    }

    fun toDomain(): Device {
        return Device(
            uuid = uuid,
            serialNumber = serialNumber,
            phoneNumber = phoneNumber,
            model = model
        )
    }

}