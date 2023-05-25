package `object`

object Player {
    private val NamePlayer = arrayOf(
        "Krisna Ranu",
        "Bernard Bear",
        "Witaradya Jaya",
        "Wisnu Oki",
        "Dwi Saputra"
    )

    private val Team = booleanArrayOf(
        false,
        true,
        true,
        false,
        false,
    )

    private val Number = intArrayOf(
        11,
        7,
        69,
        34,
        2,
    )

    /*
    0 = goal
    1 = goal pinalty
    2 = yellow card
    3 = red card
     */
    private val EventPlayer = intArrayOf(
        0,
        0,
        1,
        2,
        3,
    )

    private val EventPlayerDescription = intArrayOf(
        0,
        0,
        1,
        2,
        3,
    )

    val listData: ArrayList<ModelPlayer>
        get() {
            val list = arrayListOf<ModelPlayer>()
            for (position in NamePlayer.indices) {
                val Player = ModelPlayer()
                Player.Name = NamePlayer[position]
                Player.Team = Team[position]
                Player.Number = Number[position]
                Player.EventPlayer = EventPlayer[position]
                Player.EventPlayerDescription = EventPlayerDescription[position]
                list.add(Player)
            }
            return list
        }
}