import { useState, useEffect } from "react";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

function getDays(year: number, month: number): string[] {
    const days = new Date(year, month, 0).getDate();
    return Array.from({ length: days }, (_, i) => `${i + 1}`);
}

interface DateSelectorProps {
  year: string;
  month: string;
  day: string;
  onChange: (type: "year" | "month" | "day", value: string) => void;
}

export default function DateSelector({ year, month, day, onChange }: DateSelectorProps) {
  const thisYear = new Date().getFullYear();
  const years = Array.from({ length: 100 }, (_, i) => `${thisYear - i}`);
  const months = Array.from({ length: 12 }, (_, i) => `${i + 1}`);

  const [days, setDays] = useState<string[]>([]);

  useEffect(() => {
    if (year && month) {
      const updatedDays = getDays(Number(year), Number(month));
      setDays(updatedDays);

      // 날짜가 초과되면 1일로 설정
      if (Number(day) > updatedDays.length) {
        onChange("day", "1");
      }
    }
  }, [year, month]);

  return (
    <div className="space-y-2 text-white">
      <Label className="text-base">생년월일</Label>
      <div className="flex gap-4 mt-2">

        {/* Year */}
        <div className="flex-1">
          <div className="flex items-center gap-2">
            <Select value={year} onValueChange={(v) => onChange("year", v)}>
              <SelectTrigger className="bg-neutral-800 text-white border-neutral-600">
                <SelectValue placeholder="선택해주세요" />
              </SelectTrigger>
              <SelectContent className="bg-neutral-800 text-white max-h-64 overflow-y-auto">
                {years.map((year) => (
                  <SelectItem key={year} value={year}>
                    {year}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
            {year && <span className="text-white">년</span>}
          </div>
        </div>

        {/* Month */}
        <div className="flex-1">
          <div className="flex items-center gap-2">
            <Select value={month} onValueChange={(v) => onChange("month", v)}>
              <SelectTrigger className="bg-neutral-800 text-white border-neutral-600">
                <SelectValue placeholder="선택해주세요" />
              </SelectTrigger>
              <SelectContent className="bg-neutral-800 text-white max-h-64 overflow-y-auto">
                {months.map((month) => (
                  <SelectItem key={month} value={month}>
                    {month}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
            {month && <span className="text-white">월</span>}
          </div>
        </div>

        {/* Day */}
        <div className="flex-1">
          <div className="flex items-center gap-2">
            <Select value={day} onValueChange={(v) => onChange("day", v)}>
              <SelectTrigger className="bg-neutral-800 text-white border-neutral-600">
                <SelectValue placeholder="선택해주세요" />
              </SelectTrigger>
              <SelectContent className="bg-neutral-800 text-white max-h-64 overflow-y-auto">
                {days.map((d) => (
                  <SelectItem key={d} value={d}>
                    {d}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
            {day && <span className="text-white">일</span>}
          </div>
        </div>

      </div>
    </div>
  );
}

