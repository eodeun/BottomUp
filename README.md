# BottomUp
BottomSheetDialogCalendarExample Library
  
[![](https://jitpack.io/v/eodeun/BottomUp.svg)](https://jitpack.io/#eodeun/BottomUp)

# How to use
#### step 1.Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:  
```gradle  
allprojects {
 repositories {
  ...
   maven { url 'https://jitpack.io' } 
   }
  }
```
#### step 2.Add the dependency  
```gradle
 implementation 'com.google.android.material:material:1.0.0'
 implementation 'com.github.eodeun:BottomUp:0.1.1'
```

To get a Git project into your build:
#### step3. Using in code 
```kotlin
// onCaller
          btn.setOnClickListener {
            BottomDate().apply {
                addOnSelectorDateListener { Year, Month, DayOfMonth ->
                    btn.text =  "${Year}년 ${Month+1}월 ${DayOfMonth}일 "
                }
            }.show(supportFragmentManager, "바닥에서 선택")
        }

        btn2.setOnClickListener {
            BottomSelector(this).apply {
                items.add((BottomSelectorItem("test")))
                items.add((BottomSelectorItem("test2")))
                items.add((BottomSelectorItem("test3")))
                items.add((BottomSelectorItem("test4")))
                items.add((BottomSelectorItem("test5")))

                addOnSelecteItem { index->
                    Log.i("jiman", "onCreate: $index")
                    btn2.text = this.items.get(index).name
                }
            }.show(supportFragmentManager,"on the Bottom")

```