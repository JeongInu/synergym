import {useState, useEffect} from "react";
import {Label} from "@/components/ui/label";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

function getDays(year: number, month: number): string[]{
    const days = new Date(year, month, 0).getDate();
    return Array.from({ length: days}, (_, i)=> `${i + 1}`);
}

export default function DateSelector() {
    const thisYear = new Date().getFullYear();
    const years = Array.from({ length: 100}, (_, i)=> `${thisYear - i}`);
    const months = Array.from({length: 12}, (_, i) => `${i + 1}`);

    const [selectedYear, setSelectedYear] = useState<string>("2000");
    const [selectedMonth, setSelectedMonth] = useState<string>("1");
    const [days, setDays] = useState<string[]>(getDays(2000,1));
    const [selectedDay, setSelectedDay] = useState<string>("1");

    useEffect(()=>{
        const updatedDays = getDays(Number(selectedYear), Number(selectedMonth));
        setDays(updatedDays);
        if(Number(selectedDay) > updatedDays.length){
            setSelectedDay("1");
        }
    }, [selectedYear, selectedMonth]);

    return (
        <div className="space-y-2 text-white">
            <Label className="text-base">생년월일</Label>
            <p className="text-sm text-neutral-400">정확한 생년월일을 선택해주세요.</p>

            <div className="grid groid-cols-3 gap-4 mt-2">

                {/** Year  */}
                <div>
                    <Label className="text-sm">년도</Label>
                    <Select value={selectedYear} onValueChange={(v:string) => setSelectedYear(v)}>
                        <SelectTrigger className="bg-neutral-800 text-white border-neutral-600">
                        </SelectTrigger>
                    </Select>
                </div>

                {/** Monthh */}
                
                {/**  Date */}

            </div>
        </div>
    )
}