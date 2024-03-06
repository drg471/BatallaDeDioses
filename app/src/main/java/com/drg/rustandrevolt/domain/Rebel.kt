package com.drg.rustandrevolt.domain

import com.drg.rustandrevolt.data.sources.room.RebelEntity
import kotlin.random.Random

data class Rebel (
    override var name : String,
    override var imageCardResource: String,
    override var imageCombatPlayerResource: String,
    override var imageCombatEnemyResource: String
) : Character (
    name,
    imageCardResource,
    imageCombatPlayerResource,
    imageCombatEnemyResource
){
    override fun attack(objective: Character, attackType: Int) {
        super.damageAttacking  = 0

        if (objective is Machine){
            super.damageAttacking += 2
        }

        when (attackType){
            normalAttack -> { // normal
                super.damageAttacking += Random.nextInt(3,7)
            }
            strongAttack -> { // fuerte
                super.damageAttacking += Random.nextInt(7,13)
                super.remainingStrongAttacks --
            }
            veryStrongAttack -> { // +fuerte
                super.damageAttacking += Random.nextInt(13,25)
                super.remainingVeryStrongAttacks --
            }
            specialAttack -> { // especial
                if (objective is Engineer){
                    super.damageAttacking += 5
                }
                super.damageAttacking += Random.nextInt(30,35)
                super.chargeForSpecialAttack = 0
            }
        }

        objective.life -= super.damageAttacking

        if (attackType != specialAttack){ // Si no es el ataque especial
            super.chargeForSpecialAttack += super.damageAttacking // El daño de ataque carga el ataque especial
        }
    }
}
fun Rebel.toEntity() = RebelEntity(
    name = name,
    imageCardResource = imageCardResource,
    imageCombatPlayerResource = imageCombatPlayerResource,
    imageCombatEnemyResource = imageCombatEnemyResource
)