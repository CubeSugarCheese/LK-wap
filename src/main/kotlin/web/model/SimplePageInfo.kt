package web.model

data class SimplePageInfo(
    val count: Int, // total page count
    val cur: Int, // current page num
    val prev: Int, // preview page num
    val next: Int, // next page num
    val has_prev: Boolean,
    val has_next: Boolean
) {
    constructor(count: Int, cur: Int) : this(
        count,
        cur,
        if (cur == 1) 1 else cur - 1,
        if (cur == count) count else cur + 1,
        cur != 1,
        cur != count
    )
}
