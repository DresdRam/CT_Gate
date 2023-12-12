package com.mayv.ctgate.utils

object Constants {

    // Drop Down Menu Options

    private val typeOptions = listOf("دورية", "تشريفة")
    private val sectionOptions = listOf("القاهرة الجديدة", "الشرق", "الغرب", "الشمال", "الجنوب")
    private val timeSectionOptions = listOf("صباحية", "مسائية", "سهاري")

    // Sections Options

    private val newCairoOptions = listOf(
        "ثوابت",
        "فرعيات"
    )
    private val eastOptions = listOf(
        "خدمات المقر",
        "المجمع الامني",
        "السادة المستشارين",
        "شارع الورش",
        "جهاز الامن الوطني",
        "خدمات احمد تيسير ( ف )",
        "منطقة البنوك ( ف )",
        "اعمال عين شمس ( ف )",
        "اعمال المطرية ( ف )",
        "اعمال مصر الجديدة ( ف )",
        "اعمال النزهة ( ف )",
        "السلام و المرج ( ف )",
        "ق.اول مدينة نصر ( ف )",
        "ق.ثاني مدينة نصر ( ف )",
        "شينزو آبي ( ف )",
        "السوق التجاري ( ف )",
        "المشاه ( ف )",
        "المطار المستحدث ( ف )",
        "المحاور ( ف )",
        "موقف السيارات الجديد ( ف )",
        "اخري"
    )
    private val westOptions = listOf(
        "مرور قصر النيل",
        "مرور الزمالك",
        "مرور السيدة زينب",
        "مرور الموسكي",
        "مرور الجمالية",
        "مرور عابدين",
        "خدمات بولاق",
        "خدمات الديوان ( تخطيط )",
        "اعمال الغرب ( ف )",
        "تحويلات الفردوس ( ف )",
        "اخري"
    )
    private val northOptions = listOf(
        "خدمات الوايلي",
        "خدمات الساحل",
        "خدمات شبرا",
        "خدمات الزيتون",
        "خدمات الحدائق",
        "خدمات كوبري اكتوبر",
        "مرور الازبكية",
        "تقاطع سكة الوايلي ( ف )",
        "معهد ناصر ( ف )",
        "الكورنيش و السبتية ( ف )",
        "قسم مرور الزاوية ( ف )",
        "اخري"
    )
    private val southOptions = listOf(
        "خدمات المعادي",
        "خدمات حلوان",
        "خدمات البساتين",
        "خدمات دار السلام",
        "خدمات الخليفة",
        "خدمات مصر القديمة",
        "مرور المعادي ( ف )",
        "مرور البساتين ( ف )",
        "مرور الخليفة ( ف )",
        "مرور مصر القديمة ( ف )",
        "مرور منشية ناصر ( ف )",
        "مرور المقطم ( ف )",
        "اخري"
    )

    fun getTypeOptions() = typeOptions
    fun getSectionOptions() = sectionOptions
    fun getTimeSectionOptions() = timeSectionOptions
    fun getNewCairoOptions() = newCairoOptions
    fun getEastOptions() = eastOptions
    fun getWestOptions() = westOptions
    fun getNorthOptions() = northOptions
    fun getSouthOptions() = southOptions
}