package lightnovelApi.model.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = IsEncrypted.IsEncryptedAsIntSerializer::class)
enum class IsEncrypted(val num: Int) {
    Enable(1),
    Disable(0);

    object IsEncryptedAsIntSerializer: KSerializer<IsEncrypted> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("is_encrypted", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): IsEncrypted {
            return when(val num = decoder.decodeInt()) {
                0 -> Disable
                1 -> Enable
                else -> throw IllegalArgumentException("invalid value of $num, must be 0 or 1")
            }
        }

        override fun serialize(encoder: Encoder, value: IsEncrypted) {
            encoder.encodeInt(value.num)
        }

    }

}