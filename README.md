
**Instructions**

Create an endpoint that will connect a device to a holder. One holder can have multiple devices on it, and a device that is already connected to a holder cannot be connected to another holder.

Assign a device to the holder

Endpoint: POST /holder/assign-device

**Request:**

{
    "holderUuid": "861a50dc-b2c1-4f77-89a0-7ac66a1fe2c5",
    "deviceUuid": "5f5897ce-1b7d-421d-a828-65cdd46897eb",
    "updatedBy": "test test"
}
